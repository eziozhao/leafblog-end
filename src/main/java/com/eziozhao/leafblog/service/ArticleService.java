package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.dto.ArticleParam;
import com.eziozhao.leafblog.mbg.entity.Article;
import com.eziozhao.leafblog.mbg.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
public interface ArticleService {
    @Transactional
    int add(ArticleParam articleParam);
    @Transactional
    int delete(List<Integer> aids, Integer state);
    @Transactional
    int update(ArticleParam articleParam);
    List<Article> getArticleList(User user, String keyword, Integer pageNum, Integer pageSize, Integer state);
    Article getArticleById(Integer id);

    int recoverArticle(Integer id);
}
