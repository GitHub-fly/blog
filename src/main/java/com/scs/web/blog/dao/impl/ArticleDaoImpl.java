package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.ArticleDao;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.service.impl.ArticleServiceImpl;
import com.scs.web.blog.util.DataUtil;
import com.scs.web.blog.util.DbUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xunmi
 * @ClassName ArticleDaoImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class ArticleDaoImpl implements ArticleDao {
    private static Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Override
    public int[] batchInsert(List<Article> articleList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_article (user_id,topic_id,title,content,cover,diamond,comments,likes,publish_time,text) VALUES(?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        articleList.forEach(article -> {
            try {
                pstmt.setLong(1, DataUtil.getUserId());
                pstmt.setLong(2, DataUtil.getTopicId());
                pstmt.setString(3, article.getTitle());
                pstmt.setString(4, article.getContent());
                pstmt.setString(5, article.getCover());
                pstmt.setInt(6, article.getDiamond());
                pstmt.setInt(7, article.getComments());
                pstmt.setInt(8, article.getLikes());
                pstmt.setObject(9, article.getPublishTime());
                pstmt.setString(10, article.getText());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("批量导入文章出错");
                e.printStackTrace();
            }
        });
        int[] result = pstmt.executeBatch();
        connection.commit();
//        DbUtil.close(null, pstmt, connection);
        return result;
    }

    @Override
    public List<Article> selectAll() throws SQLException {
        List<Article> articleList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_article ORDER BY id DESC ";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            Article article = new Article();
            article.setId(rs.getLong("id"));
            article.setUserId(rs.getLong("user_id"));
            article.setTopicId(rs.getLong("topic_id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCover(rs.getString("cover"));
            article.setDiamond(rs.getInt("diamond"));
            article.setComments(rs.getInt("comments"));
            article.setLikes(rs.getInt("likes"));
            article.setPublishTime(rs.getTimestamp("publish_time").toLocalDateTime());
            article.setText(rs.getString("text"));
            articleList.add(article);
        }
//        DbUtil.close(rs, stmt, connection);
        return articleList;
    }

    @Override
    public List<ArticleVo> getHotArticle() throws SQLException {
        List<ArticleVo> articleList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT a.id, a.user_id, a.topic_id, a.title, a.content, a.cover, a.diamond, a.comments, a.likes, a.publish_time, b.nickname\n" +
                "FROM t_article a\n" +
                "LEFT JOIN t_user b\n" +
                "ON a.user_id = b.id\n" +
                "ORDER BY a.diamond DESC LIMIT 20 ";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            ArticleVo article = new ArticleVo();
            article.setId(rs.getLong("id"));
            article.setUserId(rs.getLong("user_id"));
            article.setTopicId(rs.getLong("topic_id"));
            article.setNickname(rs.getString("nickname"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCover(rs.getString("cover"));
            article.setDiamond(rs.getInt("diamond"));
            article.setComments(rs.getInt("comments"));
            article.setLikes(rs.getInt("likes"));
            article.setPublishTime(rs.getTimestamp("publish_time").toLocalDateTime());
            articleList.add(article);
        }
        return articleList;
    }

    @Override
    public Article getArticleById(Long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_article WHERE id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        Article article = new Article();
        while (rs.next()) {
            article.setId(rs.getLong("id"));
            article.setUserId(rs.getLong("user_id"));
            article.setTopicId(rs.getLong("topic_id"));
            article.setTitle(rs.getString("title"));
            article.setContent(rs.getString("content"));
            article.setCover(rs.getString("cover"));
            article.setDiamond(rs.getInt("diamond"));
            article.setComments(rs.getInt("comments"));
            article.setLikes(rs.getInt("likes"));
            article.setPublishTime(rs.getTimestamp("publish_time").toLocalDateTime());
            article.setText(rs.getString("text"));
        }
        return article;
    }

}
