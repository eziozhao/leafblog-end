package com.eziozhao.leafblog.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/7
 */
public interface TagsDao {
    int insertTagsIgnoreDup(@Param("tags") List<String> tags);
    List<Integer> selectTagsIdByTagsName(@Param("tagsName") List<String> tagsName);
}
