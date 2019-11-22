package com.scs.web.blog.dao;

import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Topic;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xunmi
 * @ClassName TopicDao
 * @Description 专题Dao
 * @Date 2019/11/22
 * @Version 1.0
 **/
public interface TopicDao {

    /**
     * 批量插入专题信息
     * @param topicList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<Topic> topicList) throws SQLException;

    /**
     * 获取所有专题信息
     * @return
     * @throws SQLException
     */
    List<Topic> selectAll() throws SQLException;

    /**
     * 获取热门专题信息
     * @return
     * @throws SQLException
     */
    List<Topic> getHotTopic() throws SQLException;
}
