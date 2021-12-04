package life.majiang.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by codedrinker on 2019/5/14.
 */
@Data
public class PaginationDTO<T> {
    private List<T> data;
    private boolean showPrevPage;
    private boolean showFirstPage;
    private boolean showNextPage;
    private boolean showEndPage;
    private Integer page;
    private Integer firstPage;
    private Integer endPage;
    private List<Integer> pages = new ArrayList<>();

    public void setPagination(Integer totalPage, Integer page) {
        this.firstPage = 1;
        this.endPage = totalPage;
        this.page = page;

        // 是否显示前一页和后一页
        showPrevPage = page > 1;
        showNextPage = page < endPage;

        int start = page - 2;
        int end = page + 2;
        if (page - 2 < 1) {
            start = 1;
            end = Math.min(start + 4, endPage);
        }
        if (page + 2 > endPage) {
            end = endPage;
            start = Math.max(1, end - 4);

        }

        // 是否显示第一页和最后一页
        showFirstPage = start > 1;
        showEndPage = end < endPage;

        for (int i = start; i <= end; i++) {
            pages.add(i);
        }
    }
}
