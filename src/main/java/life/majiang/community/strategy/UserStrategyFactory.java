package life.majiang.community.strategy;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserStrategyFactory {

    @Autowired
    private List<UserStrategy> strategies;

    public UserStrategy getStrategy(String type) {
        for (UserStrategy strategy : strategies) {
            if (StringUtils.equals(strategy.getSupportedType(), type)) {
                return strategy;
            }
        }
        return null;
    }
}
