package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.common.ExceptionCast;
import com.eziozhao.leafblog.common.ResultCode;
import com.eziozhao.leafblog.dto.MenuTreeNode;
import com.eziozhao.leafblog.mbg.entity.Menu;
import com.eziozhao.leafblog.service.MenuService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @PostMapping("/add")
    public CommonResult add(@RequestBody Menu menu) {
        int res = menuService.add(menu);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam(value = "id") Integer id) {
        int res = menuService.delete(id);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody Menu menu) {
        int res = menuService.update(menu);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }


    @GetMapping("/{id}")
    public CommonResult<Menu> getInfo(@PathVariable Integer id) {
        Menu menu = menuService.getInfo(id);
        return CommonResult.success(menu);
    }

    @GetMapping("/list/{parentId}")
    public CommonResult<List<Menu>> getList(@PathVariable Integer parentId) {
        List<Menu> menuList = menuService.getList(parentId);
        return CommonResult.success(menuList);
    }

    @GetMapping("/tree")
    public CommonResult<List<MenuTreeNode>> getMenuTree() {
        List<MenuTreeNode> menus = menuService.getMenuTree();
        return CommonResult.success(menus);
    }

}
