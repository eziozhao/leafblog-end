package com.eziozhao.leafblog.service.impl;

import com.eziozhao.leafblog.common.ExceptionCast;
import com.eziozhao.leafblog.dto.MenuTreeNode;
import com.eziozhao.leafblog.mbg.entity.Menu;
import com.eziozhao.leafblog.mbg.entity.MenuExample;
import com.eziozhao.leafblog.mbg.mapper.MenuMapper;
import com.eziozhao.leafblog.service.MenuService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eziozhao
 * @date 2020/6/20
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;


    @Override
    public int add(Menu menu) {
        menu.setCreateTime(new Date());
        if (menu.getParentId() == 0) {
            menu.setLevel(0);
        } else {
            menu.setLevel(1);
        }
        return menuMapper.insertSelective(menu);
    }

    @Override
    public int delete(Integer id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Menu menu) {
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public Menu getInfo(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> getList(Integer parentId) {
        MenuExample example = new MenuExample();
        example.createCriteria().andParentIdEqualTo(parentId);
        List<Menu> menuList = menuMapper.selectByExample(example);
        return menuList;
    }

    @Override
    public List<MenuTreeNode> getMenuTree() {
        List<Menu> menus = menuMapper.selectByExample(new MenuExample());
        List<MenuTreeNode> menuTreeNodes = menus.stream().filter(menu -> menu.getParentId().equals(0))
                .map(menu -> getChildrenNode(menu, menus)).collect(Collectors.toList());
        return menuTreeNodes;
    }

    private MenuTreeNode getChildrenNode(Menu menu, List<Menu> menuList) {
        MenuTreeNode node = new MenuTreeNode();
        BeanUtils.copyProperties(menu, node);
        List<MenuTreeNode> children = menuList.stream().filter(sub -> sub.getParentId().equals(menu.getId()))
                .map(sub -> getChildrenNode(sub, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}