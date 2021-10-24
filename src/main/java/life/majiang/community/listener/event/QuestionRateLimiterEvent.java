package life.majiang.community.listener.event;

import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class QuestionRateLimiterEvent extends ApplicationEvent {
    private Long userId;

    public QuestionRateLimiterEvent(Object source, Long userId) {
        super(source);
        this.userId = userId;
    }
}
