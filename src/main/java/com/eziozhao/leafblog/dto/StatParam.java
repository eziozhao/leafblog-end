package com.eziozhao.leafblog.dto;


import lombok.Data;

import java.util.Date;

/**
 * @author eziozhao
 * @date 2020/10/28
 */
@Data
public class StatParam {
    private Date days;
    private Integer count;
}
