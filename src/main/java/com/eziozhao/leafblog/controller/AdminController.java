package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonPage;
import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.mbg.entity.User;
import com.eziozhao.leafblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/16
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/userlist")
    public CommonResult<CommonPage<User>> getUserList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "keyword", required = false) String keyword) {
        List<User> userList = adminService.getUserList(pageNum, pageSize, keyword);
        return CommonResult.success(CommonPage.restPage(userList));
    }

    @PutMapping("/disable")
    public CommonResult disableUser(Integer userId, Integer enable) {
        int res = adminService.disableUser(userId, enable);
        if (res == 1) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @PutMapping("/update")
    public CommonResult updateUser(@RequestBody User user) {
        int res = adminService.updateUser(user);
        if (res == 1) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    @DeleteMapping("/delete")
    public CommonResult deleteUser(@RequestParam(value = "userId") Integer userId) {
        int res = adminService.deleteUser(userId);
        if (res > 0) {
            return CommonResult.success();
        }
        return CommonResult.fail();
    }

    /**
     * 给用户分配角色
     *
     * @param userId
     * @param roleIds
     * @return
     */
    @PostMapping("/user/allocRole")
    public CommonResult updateUserRole(@RequestParam(value = "userId") Integer userId,
                                       @RequestParam(value = "roleIds") List<Integer> roleIds) {
        int res = adminService.updateUserRole(userId, roleIds);
        if (res == -1) {
            return CommonResult.paramError();
        }
        return CommonResult.success();
    }

    /**
     * 给角色分配菜单
     *
     * @param roleId
     * @param menuIds
     * @return
     */
    @PostMapping("/role/allocMenu")
    public CommonResult allocMenu(@RequestParam(value = "roleId") Integer roleId,
                                  @RequestParam(value = "menuIds") List<Integer> menuIds) {
        int count = adminService.allocMenu(roleId, menuIds);
        return CommonResult.success(count);
    }

    /**
     * 给角色分配资源
     *
     * @param roleId
     * @param resourceIds
     * @return
     */
    @PostMapping("/role/allocResource")
    public CommonResult allocResource(@RequestParam(value = "roleId") Integer roleId,
                                      @RequestParam(value = "resourceIds") List<Integer> resourceIds) {
        int count = adminService.allocResource(roleId, resourceIds);
        return CommonResult.success(count);
    }
}
