package com.eziozhao.leafblog.common;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author eziozhao
 * @date 2020/7/20
 */
public class CustomException extends RuntimeException {
    @Autowired
    ResultCode resultCode;

    public CustomException(ResultCode resultCode) {
        this.resultCode = resultCode;
    }

    public CustomException(String message) {
        super(message);
    }

    public ResultCode getResultCode() {
        return resultCode;
    }
}
