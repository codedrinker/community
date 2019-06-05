package life.majiang.community.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by codedrinker on 2019/6/5.
 */
@Data
public class TagDTO {
    private String categoryName;
    private List<String> tags;
}
