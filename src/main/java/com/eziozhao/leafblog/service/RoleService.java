package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.mbg.entity.Menu;
import com.eziozhao.leafblog.mbg.entity.Resource;
import com.eziozhao.leafblog.mbg.entity.Roles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
public interface RoleService {
    List<Roles> getAllRole(Integer pageNum, Integer pageSize, String keyword);

    int add(Roles roles);

    int update(Roles roles);

    @Transactional
    int delete(Integer id);

    List<Menu> fetchMenuByRole(Integer id);

    List<Roles> fetchByUserId(Integer userId);

    List<Resource> fetchResourceByRole(Integer roleId);
}
