package life.majiang.community.provider;

import cn.ucloud.ufile.UfileClient;
import cn.ucloud.ufile.api.object.ObjectConfig;
import cn.ucloud.ufile.auth.ObjectAuthorization;
import cn.ucloud.ufile.auth.UfileObjectLocalAuthorization;
import cn.ucloud.ufile.bean.PutObjectResultBean;
import life.majiang.community.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by codedrinker on 2019/6/28.
 */
@Slf4j
@Service
public class UFileService {

    @Autowired
    private UFileProperties uFileProperties;

    /**
     * 通过 HTTP 图片 URL 上传
     *
     * @param url HTTP 图片 URL
     * @return
     * @throws FileNotFoundException
     */
    public UFileResult upload(String url) {
        File newFile = FileUtils.newFile(url);
        assert newFile != null;
        UFileResult fileResult;
        try {
            fileResult = upload(new FileInputStream(newFile), "image/png", newFile.getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException("new file exception", e);
        }
        FileUtils.deleteFile(newFile);
        return fileResult;
    }

    /**
     * 上传
     *
     * @param fileStream 文件的 input 流
     * @param mimeType   文件类型 eg. image/png
     * @param fileName   文件名称
     * @return
     */
    public UFileResult upload(InputStream fileStream, String mimeType, String fileName) {
        try {

            if (uFileProperties.getPublicKey() == null) {
                throw new RuntimeException("ucloud.ufile.publicKey is missing.");
            }

            if (uFileProperties.getPrivateKey() == null) {
                throw new RuntimeException("ucloud.ufile.privateKey is missing.");
            }
            if (uFileProperties.getUploadDomain() == null) {
                throw new RuntimeException("ucloud.ufile.uploadDomain is missing, eg. bucketname.ufile.cn-north-04.ucloud.cn.");
            }

            if (uFileProperties.getBucketName() == null) {
                throw new RuntimeException("ucloud.ufile.bucketName is missing.");
            }
            if (uFileProperties.getDownloadDomain() == null) {
                throw new RuntimeException("ucloud.ufile.downloadDomain is missing, eg. bucketname.cn-bj.ufileos.com.");
            }
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(uFileProperties.getPublicKey(), uFileProperties.getPrivateKey());
            ObjectConfig config = new ObjectConfig(uFileProperties.getUploadDomain());
            String keyName = FileUtils.newUUIDFileName(fileName);
            PutObjectResultBean response = UfileClient.object(objectAuthorization, config)
                    .putObject(fileStream, mimeType)
                    .nameAs(keyName)
                    .toBucket(uFileProperties.getBucketName())
                    .setOnProgressListener((bytesWritten, contentLength) -> {
                    })
                    .execute();
            if (response != null && response.getRetCode() == 0) {
                UFileResult fileResult = new UFileResult();
                if (uFileProperties.getBucketType() != null && StringUtils.equals(uFileProperties.getBucketType(), "private")) {
                    if (uFileProperties.getExpiresDuration() == null) {
                        throw new RuntimeException("ucloud.ufile.expiresDuration is missing, eg. 1000.");
                    }
                    fileResult.setFileName(keyName);
                    fileResult.setFileUrl(UfileClient.object(objectAuthorization, new ObjectConfig(uFileProperties.getDownloadDomain()))
                            .getDownloadUrlFromPrivateBucket(keyName, uFileProperties.getBucketName(), uFileProperties.getExpiresDuration())
                            .createUrl());
                    return fileResult;
                } else {
                    fileResult.setFileName(keyName);
                    fileResult.setFileUrl(UfileClient.object(objectAuthorization, new ObjectConfig(uFileProperties.getDownloadDomain()))
                            .getDownloadUrlFromPublicBucket(keyName, uFileProperties.getBucketName())
                            .createUrl());
                    return fileResult;
                }
            } else {
                log.error("upload error,{}", response);
                return null;
            }
        } catch (Exception e) {
            log.error("upload error,{}", fileName, e);
            return null;
        }
    }

    /**
     * 通过文件名转换离职
     *
     * @param fileName 文件名称
     * @return
     */
    public UFileResult exchangeFileUrl(String fileName) {
        try {

            if (uFileProperties.getPublicKey() == null) {
                throw new RuntimeException("ucloud.ufile.publicKey is missing.");
            }

            if (uFileProperties.getPrivateKey() == null) {
                throw new RuntimeException("ucloud.ufile.privateKey is missing.");
            }
            if (uFileProperties.getUploadDomain() == null) {
                throw new RuntimeException("ucloud.ufile.uploadDomain is missing, eg. bucketname.ufile.cn-north-04.ucloud.cn.");
            }

            if (uFileProperties.getBucketName() == null) {
                throw new RuntimeException("ucloud.ufile.bucketName is missing.");
            }
            if (uFileProperties.getDownloadDomain() == null) {
                throw new RuntimeException("ucloud.ufile.downloadDomain is missing, eg. bucketname.cn-bj.ufileos.com.");
            }
            UFileResult fileResult = new UFileResult();
            ObjectAuthorization objectAuthorization = new UfileObjectLocalAuthorization(uFileProperties.getPublicKey(), uFileProperties.getPrivateKey());
            if (uFileProperties.getBucketType() != null && StringUtils.equals(uFileProperties.getBucketType(), "private")) {
                if (uFileProperties.getExpiresDuration() == null) {
                    throw new RuntimeException("ucloud.ufile.expiresDuration is missing, eg. 1000.");
                }

                fileResult.setFileName(fileName);
                fileResult.setFileUrl(UfileClient.object(objectAuthorization, new ObjectConfig(uFileProperties.getDownloadDomain()))
                        .getDownloadUrlFromPrivateBucket(fileName, uFileProperties.getBucketName(), uFileProperties.getExpiresDuration())
                        .createUrl());
                return fileResult;
            } else {
                fileResult.setFileName(fileName);
                fileResult.setFileUrl(UfileClient.object(objectAuthorization, new ObjectConfig(uFileProperties.getDownloadDomain()))
                        .getDownloadUrlFromPublicBucket(fileName, uFileProperties.getBucketName())
                        .createUrl());
                return fileResult;
            }
        } catch (Exception e) {
            log.error("upload error,{}", fileName, e);
            return null;
        }
    }
}
