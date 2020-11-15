package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.mbg.entity.Category;
import com.eziozhao.leafblog.mbg.entity.CategoryExample;
import com.eziozhao.leafblog.mbg.mapper.CategoryMapper;
import com.eziozhao.leafblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/15
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int add(String name) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andCatenameEqualTo(name);
        int count = (int) categoryMapper.countByExample(example);
        if (count > 0) {
            return count;
        }
        Category category = new Category();
        category.setCatename(name);
        category.setDate(new Date());
        return categoryMapper.insert(category);
    }

    @Override
    public int delete(List<Integer> ids) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andIdIn(ids);
        return categoryMapper.deleteByExample(categoryExample);
    }

    @Override
    public int update(Integer id, String name) {
        Category category = new Category();
        category.setId(id);
        category.setDate(new Date());
        category.setCatename(name);
        return categoryMapper.updateByPrimaryKey(category);
    }

    @Override
    public List<Category> getAllCategory() {
        return categoryMapper.selectByExample(new CategoryExample());
    }
}
