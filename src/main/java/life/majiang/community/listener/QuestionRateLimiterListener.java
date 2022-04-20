package life.majiang.community.listener;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import life.majiang.community.listener.event.QuestionRateLimiterEvent;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.QuestionExample;
import life.majiang.community.model.User;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class QuestionRateLimiterListener implements ApplicationListener<QuestionRateLimiterEvent> {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionMapper questionMapper;

    private static Cache<Long, Integer> disableUsers = CacheBuilder.newBuilder()
            .maximumSize(10)
            .expireAfterWrite(1, TimeUnit.HOURS)
            .build();

    @SneakyThrows
    @Override
    public void onApplicationEvent(QuestionRateLimiterEvent event) {
        Integer count = disableUsers.get(event.getUserId(), () -> 0);
        disableUsers.put(event.getUserId(), count + 1);
        log.info("receive rate limit event : {}, count : {}", event.getUserId(), count);
        if (count >= 60) {
            User user = userMapper.selectByPrimaryKey(event.getUserId());
            if (user != null) {
                user.setDisable(1);
                log.info("disable user {}", event.getUserId());
                userMapper.updateByPrimaryKey(user);
            }
            QuestionExample example = new QuestionExample();
            example.createCriteria().andCreatorEqualTo(event.getUserId());
            List<Question> questions = questionMapper.selectByExample(example);
            if (questions != null && questions.size() != 0) {
                for (Question question : questions) {
                    log.info("disable user {} and delete posts {}", event.getUserId(), question.getId());
                    questionMapper.deleteByPrimaryKey(question.getId());
                }
            }
        }
    }
}
