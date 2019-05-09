package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019/4/24.
 */
@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarUrl;
}
