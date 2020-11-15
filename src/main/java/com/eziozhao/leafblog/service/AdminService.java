package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.mbg.entity.User;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/17
 */
public interface AdminService {
    List<User> getUserList(Integer pageNum, Integer pageSize, String keyword);

    int disableUser(Integer userId, Integer enable);

    int deleteUser(Integer userId);

    int updateUserRole(Integer userId, List<Integer> roleIds);

    int allocMenu(Integer roleId, List<Integer> menuIds);

    int allocResource(Integer roleId, List<Integer> resourceIds);

    int updateUser(User user);

}
