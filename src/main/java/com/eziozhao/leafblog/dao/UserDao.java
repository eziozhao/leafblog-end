package com.eziozhao.leafblog.dao;

import com.eziozhao.leafblog.mbg.entity.Menu;
import com.eziozhao.leafblog.mbg.entity.User;

import java.util.List;

/**
 * @author eziozhao.
 * @data 2020/6/4.
 */
public interface UserDao {
    int insertReturnId(User record);
    List<Menu> getMenuListByUserId(Integer userId);
}
