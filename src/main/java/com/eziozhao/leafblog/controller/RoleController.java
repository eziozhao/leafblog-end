package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonPage;
import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.mbg.entity.Menu;
import com.eziozhao.leafblog.mbg.entity.Resource;
import com.eziozhao.leafblog.mbg.entity.Roles;
import com.eziozhao.leafblog.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping("/all")
    public CommonResult<CommonPage<Roles>> getAllRole(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "keyword", required = false) String keyword) {
        List<Roles> roles = roleService.getAllRole(pageNum, pageSize, keyword);
        return CommonResult.success(CommonPage.restPage(roles));
    }

    @GetMapping("/fetchByUserId")
    public CommonResult<List<Roles>> fetchByUserId(@RequestParam(value = "userId") Integer userId) {
        List<Roles> roles = roleService.fetchByUserId(userId);
        return CommonResult.success(roles);
    }

    @PostMapping("/add")
    public CommonResult add(@RequestBody Roles roles) {
        int res = roleService.add(roles);
        if (res == 1) {
            return CommonResult.success();
        }
        return CommonResult.paramError();
    }

    @PutMapping("/update")
    public CommonResult update(@RequestBody Roles roles) {
        int res = roleService.update(roles);
        if (res == 1) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @DeleteMapping("/delete")
    public CommonResult delete(Integer roleId) {
        int res = roleService.delete(roleId);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @GetMapping("/menuList")
    public CommonResult<List<Menu>> fetchMenuByRole(@RequestParam(value = "roleId") Integer roleId) {
        List<Menu> menuList = roleService.fetchMenuByRole(roleId);
        return CommonResult.success(menuList);
    }

    @GetMapping("/resourceList")
    public CommonResult<List<Resource>> fetchResourceByRole(@RequestParam(value = "roleId") Integer roleId){
        List<Resource> resourceList = roleService.fetchResourceByRole(roleId);
        return CommonResult.success(resourceList);
    }


}
