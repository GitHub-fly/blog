package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.TopicDao;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xunmi
 * @ClassName TopicServiceImpl
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
public class TopicServiceImpl implements TopicService {
    public static Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);
    public TopicDao topicDao = DaoFactory.getTopicDaoInstance();

    @Override
    public List<Topic> hotTopic() {
        List<Topic> topicList = null;
        try {
            topicList = topicDao.getHotTopic();
            if (topicList.size() > 0) {
                logger.info("成功获取推荐专题信息");
            }
        } catch (SQLException e) {
            logger.error("获取推荐专题信息出错");
        }
        return topicList;
    }
}
