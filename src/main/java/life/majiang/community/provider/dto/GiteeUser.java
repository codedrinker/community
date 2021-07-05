package life.majiang.community.provider.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019/4/24.
 */
@Data
public class GiteeUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
