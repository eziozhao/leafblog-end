package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.dao.RolesUserDao;
import com.eziozhao.leafblog.mbg.entity.*;
import com.eziozhao.leafblog.mbg.mapper.RoleMenuMapper;
import com.eziozhao.leafblog.mbg.mapper.RoleResourceMapper;
import com.eziozhao.leafblog.mbg.mapper.RolesUserMapper;
import com.eziozhao.leafblog.mbg.mapper.UserMapper;
import com.eziozhao.leafblog.service.AdminService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/17
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RolesUserMapper rolesUserMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private RoleResourceMapper roleResourceMapper;
    @Autowired
    private RolesUserDao rolesUserDao;
    @Override
    public List<User> getUserList(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andUsernameLike("%" + keyword + "%");
            example.or(example.createCriteria().andNicknameLike("%" + keyword + "%"));
        }
        return userMapper.selectByExample(example);

    }

    @Override
    public int disableUser(Integer userId, Integer enable) {
        User user = new User();
        user.setId(userId);
        user.setEnabled(enable);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Integer userId) {
        return userMapper.deleteByPrimaryKey(userId);
    }

    @Override
    public int updateUserRole(Integer userId, List<Integer> roleIds) {
        if (roleIds == null || CollectionUtils.isEmpty(roleIds)) {
            return -1;
        }
        //删除原有的角色
        RolesUserExample rolesUserExample = new RolesUserExample();
        rolesUserExample.createCriteria().andUidEqualTo(userId);
        rolesUserMapper.deleteByExample(rolesUserExample);
        //插入新角色
        List<RolesUser> rolesUsers = new ArrayList<>();
        for (Integer id : roleIds) {
            RolesUser rolesUser = new RolesUser();
            rolesUser.setUid(userId);
            rolesUser.setRid(id);
            rolesUsers.add(rolesUser);
        }
        return rolesUserDao.insertRolesUserList(rolesUsers);
    }

    @Override
    public int allocMenu(Integer roleId, List<Integer> menuIds) {
        RoleMenuExample example = new RoleMenuExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleMenuMapper.deleteByExample(example);
        for (Integer id : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setMenuId(id);
            roleMenu.setRoleId(roleId);
            roleMenuMapper.insert(roleMenu);
        }
        return menuIds.size();
    }

    @Override
    public int allocResource(Integer roleId, List<Integer> resourceIds) {
        RoleResourceExample example = new RoleResourceExample();
        example.createCriteria().andRoleIdEqualTo(roleId);
        roleResourceMapper.deleteByExample(example);
        for (Integer id : resourceIds) {
            RoleResource roleResource = new RoleResource();
            roleResource.setResourceId(id);
            roleResource.setRoleId(roleId);
            roleResourceMapper.insert(roleResource);
        }
        return resourceIds.size();
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
