package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.dto.StatParam;

import java.util.List;
import java.util.Map;

/**
 * @author eziozhao
 * @date 2020/10/28
 */
public interface StatisticService {
    List<StatParam> getStatistics();

    List<Integer> getNums();
}
