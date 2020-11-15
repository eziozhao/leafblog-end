package com.eziozhao.leafblog.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


/**
 * @author eziozhao.
 * @data 2020/6/4.
 */
@Getter
@Setter
public class RegisterParam {

    @NotEmpty(message = "用户名不能为空")
    private String username;
    @NotEmpty(message = "密码不能为空")
    private String password;
    private String nickname;
    @Email
    private String email;
    @Range(min = 0, max = 1)
    private Integer enabled;

}
