package com.eziozhao.leafblog.dao;

import com.eziozhao.leafblog.dto.StatParam;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/10/28
 */
public interface StatisticDao {
    List<StatParam> getStatistics();

    List<Integer> getNums();
}
