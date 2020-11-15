package com.eziozhao.leafblog.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author eziozhao
 * @date 2020/5/30
 */
@Configuration
@MapperScan({"com.eziozhao.leafblog.mbg.mapper","com.eziozhao.leafblog.dao"})
public class MapperConfig {
}
