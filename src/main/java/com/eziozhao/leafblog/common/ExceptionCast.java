package com.eziozhao.leafblog.common;

/**
 * @author eziozhao
 * @date 2020/7/20
 */
public class ExceptionCast {
    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
    public static void cast(String message){
        throw new CustomException(message);
    }
}
