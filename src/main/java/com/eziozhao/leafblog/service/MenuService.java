package com.eziozhao.leafblog.service;

import com.eziozhao.leafblog.dto.MenuTreeNode;
import com.eziozhao.leafblog.mbg.entity.Menu;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author eziozhao.
 * @date 2020/6/4.
 */
public interface MenuService {

    int add(Menu menu);

    int delete(Integer id);

    int update(Menu menu);

    Menu getInfo(Integer id);

    List<Menu> getList(Integer parentId);

    List<MenuTreeNode> getMenuTree();
}
