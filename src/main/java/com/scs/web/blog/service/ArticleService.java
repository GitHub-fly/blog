package com.scs.web.blog.service;

import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;

import java.util.List;

/**
 * @author xunmi
 * @ClassName ArticleService
 * @Description 文章业务逻辑
 * @Date 2019/11/9
 * @Version 1.0
 **/
public interface ArticleService {

    /**
     * 初始化图书信息
     * @return
     */
    List<Article> initArticle();

    /**
     * 获取热门文章
     * @return
     */
    List<ArticleVo> hotArticle();

    /**
     * 获取指定id的文章信息
     * @param id 指定文章id
     * @return
     */
    Article articleById(Long id);
}
