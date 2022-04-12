package life.majiang.community.strategy;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.provider.GithubProvider;
import life.majiang.community.provider.UFileResult;
import life.majiang.community.provider.UFileService;
import life.majiang.community.provider.dto.GithubUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GithubUserStrategy implements UserStrategy {
    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private UFileService uFileService;

    @Override
    public LoginUserInfo getUser(String code, String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(accessToken);
        LoginUserInfo loginUserInfo = new LoginUserInfo();
        loginUserInfo.setAvatarUrl(githubUser.getAvatarUrl());
        loginUserInfo.setBio(githubUser.getBio());
        loginUserInfo.setId(githubUser.getId());
        loginUserInfo.setName(githubUser.getName());
        UFileResult fileResult = null;
        try {
            fileResult = uFileService.upload(loginUserInfo.getAvatarUrl());
            loginUserInfo.setAvatarUrl(fileResult.getFileUrl());
        } catch (Exception e) {
            log.error("upload image error", e);
            loginUserInfo.setAvatarUrl(loginUserInfo.getAvatarUrl());
        }
        return loginUserInfo;
    }

    @Override
    public String getSupportedType() {
        return "github";
    }
}
