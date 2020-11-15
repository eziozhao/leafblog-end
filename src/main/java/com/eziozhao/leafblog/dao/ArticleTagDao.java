package com.eziozhao.leafblog.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/7
 */
public interface ArticleTagDao {
    int addArticleTag(@Param("tagIds") List<Integer> tagIds,@Param("articleId") Integer aid);
}
