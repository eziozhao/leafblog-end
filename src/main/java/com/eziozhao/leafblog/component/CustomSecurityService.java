package com.eziozhao.leafblog.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @author eziozhao
 * @date 2020/7/26
 */
public interface CustomSecurityService {
    Map<String, ConfigAttribute> fetchResourceMap();
}
