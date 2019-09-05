package life.majiang.community.service;

import life.majiang.community.mapper.AdMapper;
import life.majiang.community.model.Ad;
import life.majiang.community.model.AdExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by codedrinker on 2019/9/6.
 */
@Service
public class AdService {
    @Autowired
    private AdMapper adMapper;

    public List<Ad> list() {
        AdExample navExample = new AdExample();
        navExample.createCriteria()
                .andStatusEqualTo(1)
                .andGmtStartLessThan(System.currentTimeMillis())
                .andGmtEndGreaterThan(System.currentTimeMillis());
        //navExample.setOrderByClause("priority desc");
        return adMapper.selectByExample(navExample);
    }
}
