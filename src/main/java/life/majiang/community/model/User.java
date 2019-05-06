package life.majiang.community.model;

import lombok.Data;

/**
 * Created by codedrinker on 2019/4/30.
 */
@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;
}
