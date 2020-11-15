package com.eziozhao.leafblog.component;

import com.eziozhao.leafblog.mbg.entity.UserLog;
import com.eziozhao.leafblog.service.UserLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author eziozhao.
 * @data 2020/8/18.
 */
@Component
public class UserLogTask {
    @Autowired
    private UserLogService userLogService;

    @Async
    public void addLog(UserLog userLog){
        userLogService.add(userLog);
    }
}
