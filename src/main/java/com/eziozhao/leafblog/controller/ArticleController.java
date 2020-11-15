package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonPage;
import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.dto.ArticleParam;
import com.eziozhao.leafblog.mbg.entity.Article;
import com.eziozhao.leafblog.mbg.entity.User;
import com.eziozhao.leafblog.service.ArticleService;
import com.eziozhao.leafblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/5/31
 */
@RestController
@RequestMapping("/article/")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private UserService userService;

    @PostMapping("add")
    public CommonResult add(@Validated @RequestBody ArticleParam articleParam) {
        if (StringUtils.isEmpty(articleParam.getArticle().getTitle())) {
            return CommonResult.fail("请设置标题");
        }
        User user = userService.getCurrentUser();
        articleParam.getArticle().setUid(user.getId());
        int res = articleService.add(articleParam);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @PostMapping("delete")
    public CommonResult delete(@RequestParam(value = "aids") List<Integer> aids,
                               @RequestParam(value = "state") Integer state) {
        int res = articleService.delete(aids, state);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @PostMapping("update")
    public CommonResult update(@RequestBody ArticleParam articleParam) {
        if (StringUtils.isEmpty(articleParam.getArticle().getTitle())) {
            return CommonResult.fail("请设置标题");
        }
        int res = articleService.update(articleParam);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @GetMapping("list")
    public CommonResult<CommonPage<Article>> getArticleList(@RequestParam(value = "keyword", required = false) String keyword,
                                                            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                            @RequestParam(value = "state", required = false) Integer state) {
        User user = userService.getCurrentUser();
        List<Article> articleList = articleService.getArticleList(user, keyword, pageNum, pageSize, state);
        return CommonResult.success(CommonPage.restPage(articleList));
    }

    @GetMapping("{id}")
    public CommonResult getArticleById(@PathVariable Integer id) {
        Article article = articleService.getArticleById(id);
        if (article != null) {
            return CommonResult.success(article);
        }
        return CommonResult.fail("文章不存在");
    }

    @PostMapping("recover")
    public CommonResult recoverArticle(Integer id) {
        int res = articleService.recoverArticle(id);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

}
