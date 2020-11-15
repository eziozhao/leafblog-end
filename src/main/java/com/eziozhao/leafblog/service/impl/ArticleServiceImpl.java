package com.eziozhao.leafblog.service.impl;

import cn.hutool.http.HtmlUtil;
import com.eziozhao.leafblog.dao.ArticleDao;
import com.eziozhao.leafblog.dao.ArticleTagDao;
import com.eziozhao.leafblog.dao.TagsDao;
import com.eziozhao.leafblog.dto.ArticleParam;
import com.eziozhao.leafblog.mbg.entity.Article;
import com.eziozhao.leafblog.mbg.entity.ArticleExample;
import com.eziozhao.leafblog.mbg.entity.ArticleTagsExample;
import com.eziozhao.leafblog.mbg.entity.User;
import com.eziozhao.leafblog.mbg.mapper.ArticleMapper;
import com.eziozhao.leafblog.mbg.mapper.ArticleTagsMapper;
import com.eziozhao.leafblog.mbg.mapper.CategoryMapper;
import com.eziozhao.leafblog.mbg.mapper.TagsMapper;
import com.eziozhao.leafblog.service.ArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author eziozhao
 * @date 2020/6/7
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    private static final int DRAFT = 0;
    private static final int BIN = 2;
    private static final int DELETE = 3;
    private static final String ADMIN = "admin";


    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagsMapper tagsMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private ArticleTagsMapper articleTagsMapper;
    @Autowired
    private TagsDao tagsDao;
    @Autowired
    private ArticleTagDao articleTagDao;

    @Override
    public int add(ArticleParam articleParam) {
        Article article = articleParam.getArticle();
        //分组设置
        article.setCid(articleParam.getCategoryId());
        //说明是发表文章
        if (article.getState() == 1) {
            article.setPublishdate(new Date());
            article.setEdittime(new Date());
        } else if (article.getState() == 0) {
            //草稿
            article.setEdittime(new Date());
        }
        int res = articleDao.insertReturnId(article);
        //tags处理
        if (articleParam.getTags() != null && articleParam.getTags().size() > 0) {
            List<String> tags = articleParam.getTags();
            addArticleTags(article.getId(), tags);
        }
        return res;
    }

    @Override
    public int delete(List<Integer> aids, Integer state) {
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andIdIn(aids);
        if (state == BIN) {
            Article article = new Article();
            article.setState(2);
            //放入回收站
            return articleMapper.updateByExampleSelective(article, articleExample);
        }
        if (state == DELETE) {
            //彻底删除
            ArticleTagsExample example = new ArticleTagsExample();
            example.createCriteria().andAidIn(aids);
            articleTagsMapper.deleteByExample(example);
            return articleMapper.deleteByExample(articleExample);
        }
        return -1;
    }

    @Override
    public int update(ArticleParam articleParam) {
        Article article = articleParam.getArticle();
        //分组设置
        article.setCid(articleParam.getCategoryId());
        //说明是发表文章
        article.setEdittime(new Date());
        ArticleExample articleExample = new ArticleExample();
        articleExample.createCriteria().andIdEqualTo(article.getId());
        int res = articleMapper.updateByExampleSelective(article, articleExample);
        if (articleParam.getTags() != null && articleParam.getTags().size() > 0) {
            addArticleTags(article.getId(), articleParam.getTags());
        }
        return res;
    }

    @Override
    public List<Article> getArticleList(User user, String keyword, Integer pageNum, Integer pageSize,
                                        Integer state) {
        PageHelper.startPage(pageNum, pageSize);
        ArticleExample articleExample = new ArticleExample();
        ArticleExample.Criteria criteria = articleExample.createCriteria();
        //管理员返回所有文章
        if (!ADMIN.equals(user.getUsername())) {
            criteria = articleExample.createCriteria().andUidEqualTo(user.getId());
        }
        if (state != null) {
            criteria.andStateEqualTo(state);
        } else {
            criteria.andStateNotEqualTo(BIN);
        }
        if (!StringUtils.isEmpty(keyword)) {
            criteria.andTitleLike("%" + keyword + "%");
        }
        return articleMapper.selectByExample(articleExample);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int recoverArticle(Integer id) {
        Article article = new Article();
        article.setId(id);
        article.setState(DRAFT);
        return articleMapper.updateByPrimaryKeySelective(article);
    }

    private void addArticleTags(Integer id, List<String> tags) {
        //删除文章现有的所有标签
        deleteCurrentTags(id);
        //将所有标签保存
        tagsDao.insertTagsIgnoreDup(tags);
        //查询tagsId
        List<Integer> tagIds = tagsDao.selectTagsIdByTagsName(tags);
        //建立新的关联
        articleTagDao.addArticleTag(tagIds, id);
    }

    private void deleteCurrentTags(Integer id) {
        ArticleTagsExample exampleDel = new ArticleTagsExample();
        exampleDel.createCriteria().andAidEqualTo(id);
        articleTagsMapper.deleteByExample(exampleDel);
    }
}
