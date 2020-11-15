package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.mbg.entity.UserLog;
import com.eziozhao.leafblog.mbg.entity.UserLogExample;
import com.eziozhao.leafblog.mbg.mapper.UserLogMapper;
import com.eziozhao.leafblog.service.UserLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author eziozhao.
 * @data 2020/8/18.
 */
@Service
public class UserLogServiceImpl implements UserLogService {
    @Autowired
    private UserLogMapper userLogMapper;

    @Override
    public void add(UserLog userLog) {
        userLogMapper.insertSelective(userLog);
    }

    @Override
    public List<UserLog> list(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserLogExample example = new UserLogExample();
        if (!StringUtils.isEmpty(keyword)) {
            example.or().andUsernameLike("%" + keyword + "%");
            example.or().andIpLike("%" + keyword + "%");
            example.or().andUrlLike("%" + keyword + "%");
        }
        return userLogMapper.selectByExample(example);
    }

    @Override
    public int delete(List<Long> ids) {
        UserLogExample example = new UserLogExample();
        example.createCriteria().andIdIn(ids);
        return userLogMapper.deleteByExample(example);
    }
}
