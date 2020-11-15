package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.dao.StatisticDao;
import com.eziozhao.leafblog.dto.StatParam;
import com.eziozhao.leafblog.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author eziozhao
 * @date 2020/10/28
 */
@Service
public class StatisticServiceImpl implements StatisticService {
    @Autowired
    private StatisticDao statisticDao;
    @Override
    public List<StatParam> getStatistics() {
        return statisticDao.getStatistics();
    }

    @Override
    public List<Integer> getNums() {
        return statisticDao.getNums();
    }
}
