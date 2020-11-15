package com.eziozhao.leafblog.dao;

import com.eziozhao.leafblog.mbg.entity.Article;

/**
 * @author eziozhao
 * @date 2020/6/7
 */
public interface ArticleDao {
    int insertReturnId(Article article);

}
