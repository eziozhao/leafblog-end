package com.eziozhao.leafblog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eziozhao
 * @date 2020/8/1
 */
@Data
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlConfig {
    private List<String> urls = new ArrayList<>();
}
