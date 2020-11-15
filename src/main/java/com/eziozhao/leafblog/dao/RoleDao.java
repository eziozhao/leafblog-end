package com.eziozhao.leafblog.dao;

import com.eziozhao.leafblog.mbg.entity.Menu;
import com.eziozhao.leafblog.mbg.entity.Resource;
import com.eziozhao.leafblog.mbg.entity.Roles;

import java.util.List;

/**
 * @author eziozhao.
 * @data 2020/8/11.
 */
public interface RoleDao {
    int deleteRoleRelation(Integer roleId);

    List<Roles> fetchByUserId(Integer userId);

    List<Menu> fetchMenuByRole(Integer id);

    List<Resource> fetchResourceByRole(Integer id);

}
