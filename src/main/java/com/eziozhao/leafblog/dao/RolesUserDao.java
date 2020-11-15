package com.eziozhao.leafblog.dao;

import com.eziozhao.leafblog.mbg.entity.RolesUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/18
 */
public interface RolesUserDao {
    int insertRolesUserList(@Param(value = "list") List<RolesUser> rolesUserList);

}
