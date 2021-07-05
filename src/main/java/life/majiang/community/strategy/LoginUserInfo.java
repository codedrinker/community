package life.majiang.community.strategy;

import lombok.Data;

@Data
public class LoginUserInfo {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
