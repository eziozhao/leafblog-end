package com.eziozhao.leafblog.dao;

import com.eziozhao.leafblog.mbg.entity.Resource;

import java.util.List;

/**
 * @author eziozhao.
 * @data 2020/8/7.
 */
public interface UserResourceDao {
    List<Resource> fetchResourceByUserId(Integer userId);
}
