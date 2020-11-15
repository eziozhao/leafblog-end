package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.common.ExceptionCast;
import com.eziozhao.leafblog.dao.RoleDao;
import com.eziozhao.leafblog.mbg.entity.*;
import com.eziozhao.leafblog.mbg.mapper.MenuMapper;
import com.eziozhao.leafblog.mbg.mapper.RoleMenuMapper;
import com.eziozhao.leafblog.mbg.mapper.RolesMapper;
import com.eziozhao.leafblog.mbg.mapper.RolesUserMapper;
import com.eziozhao.leafblog.service.RoleService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eziozhao
 * @date 2020/6/19
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RolesMapper rolesMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private RolesUserMapper rolesUserMapper;

    @Override
    public List<Roles> getAllRole(Integer pageNum, Integer pageSize, String keyword) {
        PageHelper.startPage(pageNum, pageSize);
        RolesExample example = new RolesExample();
        RolesExample.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andNameLike("%" + keyword + "%");
            example.or(example.createCriteria().andDescriptionLike("%" + keyword + "%"));
        }
        return rolesMapper.selectByExample(example);
    }

    @Override
    public int add(Roles roles) {
        if (StringUtils.isEmpty(roles.getName())) {
            return -1;
        }
        roles.setCreateTime(new Date());
        return rolesMapper.insert(roles);
    }

    @Override
    public int update(Roles roles) {
        return rolesMapper.updateByPrimaryKeySelective(roles);
    }

    @Override
    public int delete(Integer id) {
        RolesUserExample example = new RolesUserExample();
        example.createCriteria().andRidEqualTo(id);
        long count = rolesUserMapper.countByExample(example);
        if (count > 0) {
            ExceptionCast.cast("该角色已分配到用户，无法删除");
        }
        return roleDao.deleteRoleRelation(id);
    }

    @Override
    public List<Menu> fetchMenuByRole(Integer id) {
        return roleDao.fetchMenuByRole(id);
    }

    @Override
    public List<Roles> fetchByUserId(Integer userId) {
        return roleDao.fetchByUserId(userId);
    }

    @Override
    public List<Resource> fetchResourceByRole(Integer roleId) {
        return roleDao.fetchResourceByRole(roleId);
    }
}
