package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xunmi
 * @ClassName ArticleServiceImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class ArticleServiceImpl implements ArticleService {

    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
    private ArticleDao articleDao = DaoFactory.getArticleDaoInstance();

    @Override
    public List<Article> initArticle() {
        List<Article> articleList = new ArrayList<>();
        try {
            articleList = articleDao.selectAll();
        } catch (SQLException e) {
            logger.error("初始化图书信息出错");
            e.printStackTrace();
        }
        logger.info("成功初始化图书信息");
        return articleList;
    }

    @Override
    public List<ArticleVo> hotArticle() {
        List<ArticleVo> articleList = new ArrayList<>();
        try {
            articleList = articleDao.getHotArticle();
        } catch (SQLException e) {
            logger.error("初始化图书信息出错");
            e.printStackTrace();
        }
        logger.info("成功初始化图书信息");
        return articleList;
    }
}
