package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.TopicDao;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.util.DataUtil;
import com.scs.web.blog.util.DbUtil;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xunmi
 * @ClassName TopicDaoImpl
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
public class TopicDaoImpl implements TopicDao {
    private static Logger logger = LoggerFactory.getLogger(TopicDaoImpl.class);

    @Override
    public int[] batchInsert(List<Topic> topicList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_topic VALUES(null,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        topicList.forEach(topic -> {
            try {
                pstmt.setLong(1, DataUtil.getUserId());
                pstmt.setString(2, topic.getTopicName());
                pstmt.setString(3, topic.getLogo());
                pstmt.setString(4, topic.getDescription());
                pstmt.setInt(5, topic.getArticles());
                pstmt.setInt(6, topic.getFollows());
                pstmt.setObject(7, topic.getCreateTime());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("批量插入专题信息出错");
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
        return result;
    }

    @Override
    public List<Topic> selectAll() throws SQLException {
        List<Topic> topicList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_topic ORDER BY id DESC ";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Topic topic = new Topic();
            topic.setId(rs.getLong("id"));
            topic.setAdminId(rs.getLong("admin_id"));
            topic.setTopicName(rs.getString("topic_name"));
            topic.setLogo(rs.getString("logo"));
            topic.setDescription(rs.getString("description"));
            topic.setArticles(rs.getInt("articles"));
            topic.setFollows(rs.getInt("follows"));
            topic.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            topicList.add(topic);
        }
//        DbUtil.close(rs, stmt, connection);
        return topicList;
    }

    @Override
    public List<Topic> getHotTopic() throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_topic ORDER BY articles DESC LIMIT 20 ";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Topic> topicList = new ArrayList<>();
        while (rs.next()) {
            Topic topic = new Topic();
            topic.setId(rs.getLong("id"));
            topic.setAdminId(rs.getLong("admin_id"));
            topic.setTopicName(rs.getString("topic_name"));
            topic.setLogo(rs.getString("logo"));
            topic.setDescription(rs.getString("description"));
            topic.setArticles(rs.getInt("articles"));
            topic.setFollows(rs.getInt("follows"));
            topic.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            topicList.add(topic);
        }
        return topicList;
    }
}
