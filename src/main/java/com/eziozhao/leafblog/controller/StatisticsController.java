package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.dto.StatParam;
import com.eziozhao.leafblog.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/10/28
 */
@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticService statisticService;

    @GetMapping("/api")
    public CommonResult getStatistics() {
        List<StatParam> list = statisticService.getStatistics();
        return CommonResult.success(list);
    }

    @GetMapping("/nums")
    public CommonResult getNums() {
        List<Integer> list = statisticService.getNums();
        return CommonResult.success(list);
    }
}
