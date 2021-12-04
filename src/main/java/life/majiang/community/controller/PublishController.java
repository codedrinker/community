package life.majiang.community.controller;

import life.majiang.community.cache.QuestionRateLimiter;
import life.majiang.community.cache.TagCache;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import life.majiang.community.service.QuestionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.DeclareRoles;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by codedrinker on 2019/5/2.
 */
@Controller
@Slf4j
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRateLimiter questionRateLimiter;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id") Long id,
                       Model model) {
        QuestionDTO question = questionService.getById(id);
        model.addAttribute("id", question.getId());
        model.addAttribute("title", question.getTitle());
        model.addAttribute("description", question.getDescription());
        model.addAttribute("tag", question.getTag());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model) {
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "tag", required = false) String tag,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("id", id);
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        model.addAttribute("tags", TagCache.get());

        if (StringUtils.isBlank(title)) {
            model.addAttribute("error", "标题不能为空");
            if (id != null) return edit(id, model);
            else return publish(model);
        }

        if (StringUtils.length(title) > 50) {
            model.addAttribute("error", "标题最多 50 个字符");
            if (id != null) return edit(id, model);
            else return publish(model);
        }
        if (StringUtils.isBlank(description)) {
            model.addAttribute("error", "问题补充不能为空");
            return "publish";
        }
        if (StringUtils.isBlank(tag)) {
            model.addAttribute("error", "标签不能为空");
            if (id != null) return edit(id, model);
            else return publish(model);
        }

        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "输入非法标签:" + invalid);
            if (id != null) return edit(id, model);
            else return publish(model);
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            if (id != null) return edit(id, model);
            else return publish(model);
        }

        if (user.getDisable() == 1) {
            model.addAttribute("error", "操作被禁用，如有疑问请联系管理员");
            if (id != null) return edit(id, model);
            else return publish(model);
        }

        if (questionRateLimiter.reachLimit(user.getId())) {
            model.addAttribute("error", "操作太快，请求被限制");
            if (id != null) return edit(id, model);
            else return publish(model);
        }

        Question question = new Question();
        question.setId(id);
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/";
    }
}
