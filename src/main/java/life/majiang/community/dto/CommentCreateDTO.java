package life.majiang.community.dto;

import lombok.Data;

/**
 * Created by codedrinker on 2019/5/30.
 */
@Data
public class CommentCreateDTO {
    private Long parentId;
    private String content;
    private Integer type;
}
