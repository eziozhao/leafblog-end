package com.eziozhao.leafblog.controller;

import com.eziozhao.leafblog.common.CommonPage;
import com.eziozhao.leafblog.common.CommonResult;
import com.eziozhao.leafblog.mbg.entity.UserLog;
import com.eziozhao.leafblog.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author eziozhao.
 * @data 2020/8/18.
 */
@RestController
@RequestMapping("/logs")
public class UserLogController {
    @Autowired
    private UserLogService userLogService;

    @GetMapping("/list")
    public CommonResult<CommonPage<UserLog>> list(@RequestParam(name = "keyword", required = false) String keyword,
                                                  @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                                  @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        List<UserLog> logs = userLogService.list(keyword, pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(logs));
    }

    @DeleteMapping("/delete")
    public CommonResult delete(@RequestParam(value = "ids") List<Long> ids) {
        int res = userLogService.delete(ids);
        if (res > 0){
            return CommonResult.success();
        }
        return CommonResult.fail();
    }


}
