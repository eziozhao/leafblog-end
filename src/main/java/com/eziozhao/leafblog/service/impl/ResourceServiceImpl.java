package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.mbg.entity.Resource;
import com.eziozhao.leafblog.mbg.entity.ResourceExample;
import com.eziozhao.leafblog.mbg.mapper.ResourceMapper;
import com.eziozhao.leafblog.service.ResourceService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author eziozhao
 * @date 2020/7/11
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public int add(Resource resource) {
        resource.setCreateTime(new Date());
        return resourceMapper.insertSelective(resource);
    }

    @Override
    public int delete(Integer id) {
        return resourceMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Resource resource) {
        return resourceMapper.updateByPrimaryKeySelective(resource);
    }

    @Override
    public Resource getInfo(Integer id) {
        return resourceMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Resource> listAll() {
        ResourceExample example = new ResourceExample();
        return resourceMapper.selectByExample(example);
    }

    @Override
    public List<Resource> getResourceList(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        ResourceExample resourceExample  = new ResourceExample();
        if(!StringUtils.isEmpty(keyword)){
            resourceExample.or().andNameLike("%"+keyword+"%");
            resourceExample.or().andUrlLike("%"+keyword+"%");
            resourceExample.or().andDescriptionLike("%"+keyword+"%");
        }
        return resourceMapper.selectByExample(resourceExample);

    }
}
