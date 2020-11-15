package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.dto.LoginParam;
import com.eziozhao.leafblog.dto.RegisterParam;
import com.eziozhao.leafblog.mbg.entity.User;
import com.eziozhao.leafblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author eziozhao
 * @date 2020/5/30
 */
@RestController
@RequestMapping("/user/")
public class UserController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserService userService;


    @PostMapping("register")
    public CommonResult register(@Validated @RequestBody RegisterParam registerParam) {
        User user = userService.register(registerParam);
        return CommonResult.success(user);
    }

    @PostMapping("login")
    public CommonResult login(@Validated @RequestBody LoginParam loginParam) {
        String res = userService.login(loginParam);
        Map<String, String> map = new HashMap<>(2);
        map.put("token", res);
        map.put("tokenHead", tokenHead);
        return CommonResult.success(map);
    }

    @PutMapping("updatePassword")
    public CommonResult updatePassword(String oldPassword, String newPassword) {
        int res = userService.updatePassword(oldPassword, newPassword);
        if (res == 1) {
            return CommonResult.success();
        } else if (res == -2) {
            return CommonResult.paramError();
        } else {
            return CommonResult.fail("旧密码错误");
        }
    }

    @GetMapping("info")
    public CommonResult fetchUserInfo() {
        User user = userService.getCurrentUser();
        if (user == null) {
            return CommonResult.authenticatedError(null);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("menus", userService.getMenuListByUserId(user.getId()));
        return CommonResult.success(map);
    }
    @GetMapping("getUserById")
    public CommonResult fetchUserById(Integer id){
        User user = userService.fetchUserById(id);
        if(user==null){
            return CommonResult.fail("用户不存在");
        }
        return CommonResult.success(user);
    }
    @PostMapping("logout")
    public CommonResult logout(){
        return CommonResult.success();
    }
}
