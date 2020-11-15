package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.mbg.entity.UserLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author eziozhao.
 * @data 2020/8/18.
 */
public interface UserLogService {
    void add(UserLog userLog);

    List<UserLog> list(String keyword, Integer pageNum, Integer pageSize);

    int delete(List<Long> ids);
}
