package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by codedrinker on 2019/5/2.
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }
}
