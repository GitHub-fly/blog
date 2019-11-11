package com.scs.web.blog.service;

import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArticleServiceTest {

    private ArticleService articleService = ServiceFactory.getArticleServiceInstance();

    @Test
    public void initArticle() {
        System.out.println(articleService.initArticle().size());
    }
}