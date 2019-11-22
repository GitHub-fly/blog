package com.scs.web.blog.dao;

import com.mysql.jdbc.log.Log;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TopicDaoTest {

    private static TopicDao topicDao = DaoFactory.getTopicDaoInstance();
    private static Logger logger = LoggerFactory.getLogger(TopicDaoTest.class);

    @Test
    public void batchInsert() throws SQLException {
        System.out.println(topicDao.batchInsert(JSoupSpider.getTopic()).length);
    }

    @Test
    public void selectAll() {
        List<Topic> list = new ArrayList<>();
        try {
            list = topicDao.selectAll();
            list.forEach(System.out::println);
        } catch (SQLException e) {
            logger.error("查询所有专题信息出错");
        }
    }

    @Test
    public void getHotTopic() throws SQLException {
        System.out.println(topicDao.getHotTopic().size());
    }
}