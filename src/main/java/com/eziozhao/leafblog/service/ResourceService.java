package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.mbg.entity.Resource;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
public interface ResourceService {
    int add(Resource resource);

    int delete(Integer id);

    int update(Resource resource);

    Resource getInfo(Integer id);

    List<Resource> listAll();

    List<Resource> getResourceList(String keyword, Integer pageNum, Integer pageSize);
}
