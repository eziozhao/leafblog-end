package com.eziozhao.leafblog.mbg.mapper;

import com.eziozhao.leafblog.mbg.entity.ArticleTags;
import com.eziozhao.leafblog.mbg.entity.ArticleTagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ArticleTagsMapper {
    long countByExample(ArticleTagsExample example);

    int deleteByExample(ArticleTagsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleTags record);

    int insertSelective(ArticleTags record);

    List<ArticleTags> selectByExample(ArticleTagsExample example);

    ArticleTags selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ArticleTags record, @Param("example") ArticleTagsExample example);

    int updateByExample(@Param("record") ArticleTags record, @Param("example") ArticleTagsExample example);

    int updateByPrimaryKeySelective(ArticleTags record);

    int updateByPrimaryKey(ArticleTags record);
}