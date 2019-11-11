package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Spliterator;

import static org.junit.Assert.*;

public class ArticleDaoTest {

    private static ArticleDao articleDao = DaoFactory.getArticleDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(ArticleDaoTest.class);

    @Test
    public void selectAll()  {
        List<Article> articleList = null;
        try {
          articleList = articleDao.selectAll();
        } catch (SQLException e) {
            logger.error("获取所有文章信息出错");
        }
        articleList.forEach(System.out::println);
        logger.info("成功初始化图书信息");
    }

    @Test
    public void batchInsert() throws SQLException {
        articleDao.batchInsert(JSoupSpider.getArticle());
    }
}