package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.mbg.entity.Category;
import com.eziozhao.leafblog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/5/31
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public CommonResult add(@RequestParam(value = "name") String name) {
        if (StringUtils.isEmpty(name)) {
            return CommonResult.paramError();
        }
        int res = categoryService.add(name);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam(value = "ids") List<Integer> ids) {
        int res = categoryService.delete(ids);
        if (res >= 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @PutMapping("/update")
    public CommonResult update(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name) {
        if (StringUtils.isEmpty(name)) {
            return CommonResult.paramError();
        }
        int res = categoryService.update(id, name);
        if (res == 1) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @GetMapping("/list")
    public CommonResult<List<Category>> getAllCategory(){
         List<Category> categories = categoryService.getAllCategory();
         return CommonResult.success(categories);
    }


}
