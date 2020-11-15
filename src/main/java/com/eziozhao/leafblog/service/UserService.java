package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.dto.LoginParam;
import com.eziozhao.leafblog.dto.RegisterParam;
import com.eziozhao.leafblog.mbg.entity.Menu;
import com.eziozhao.leafblog.mbg.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
public interface UserService {
    /**
     * 注册
     *
     * @param registerParam
     * @return
     */
    User register(RegisterParam registerParam);

    /**
     * 登录
     *
     * @param loginParam
     * @return token
     */
    String login(LoginParam loginParam);

    User getUserByUsername(String name);

    int updatePassword(String oldPassword, String newPassword);

    UserDetails loadUserByUsername(String username);

    User getCurrentUser();

    List<Menu> getMenuListByUserId(Integer id);

    User fetchUserById(Integer id);
}
