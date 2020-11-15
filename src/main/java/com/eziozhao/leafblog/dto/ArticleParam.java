package com.eziozhao.leafblog.dto;

import com.eziozhao.leafblog.mbg.entity.Article;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/7
 */
@Getter
@Setter
public class ArticleParam {
    @NotNull(message = "文章不能为空")
    private Article article;
    private List<String> tags;
    @NotNull(message = "分类不能为空")
    private Integer categoryId;
 }
