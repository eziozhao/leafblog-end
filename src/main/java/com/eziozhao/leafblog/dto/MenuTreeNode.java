package com.eziozhao.leafblog.dto;

import com.eziozhao.leafblog.mbg.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author eziozhao
 * @date 2020/7/6
 */
@Getter
@Setter
public class MenuTreeNode extends Menu {
    private List<MenuTreeNode> children;
}
