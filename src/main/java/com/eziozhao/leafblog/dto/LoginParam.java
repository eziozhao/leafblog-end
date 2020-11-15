package com.eziozhao.leafblog.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author eziozhao.
 * @data 2020/6/5.
 */
@Getter
@Setter
public class LoginParam {
    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
}
