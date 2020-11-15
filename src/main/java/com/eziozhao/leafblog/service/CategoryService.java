package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.mbg.entity.Category;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
public interface CategoryService {
    int add(String name);
    int delete(List<Integer> ids);
    int update(Integer id,String name);
    List<Category> getAllCategory();
}
