package life.majiang.community.schedule;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.model.UserExample;
import life.majiang.community.provider.UFileResult;
import life.majiang.community.provider.UFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by codedrinker on 2019/8/1.
 */
@Component
@Slf4j
public class AvatarFixTasks {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UFileService uFileService;

    @Scheduled(fixedRate = 1000 * 60 * 10)
    public void fixUserAvatar() {
        int offset = 0;
        int limit = 20;
        log.info("fixUserAvatar start {}", new Date());
        List<User> list = new ArrayList<>();
        while (offset == 0 || list.size() == limit) {
            UserExample userExample = new UserExample();
            userExample.setOrderByClause("id desc");
            list = userMapper.selectByExampleWithRowbounds(userExample, new RowBounds(offset, limit));
            for (User user : list) {
                try {
                    if (StringUtils.contains(user.getAvatarUrl(), "githubusercontent")) {
                        UFileResult fileResult = uFileService.upload(user.getAvatarUrl());
                        if (fileResult != null && fileResult.getFileUrl() != null) {
                            user.setAvatarUrl(fileResult.getFileUrl());
                            log.error("fixUserAvatar user : {}", user.getId());
                            userMapper.updateByPrimaryKey(user);
                        }
                    }
                } catch (Exception e) {
                    log.error("fixUserAvatar error", e);
                }
            }
            offset += limit;
        }
        log.info("fixUserAvatar stop {}", new Date());
    }
}