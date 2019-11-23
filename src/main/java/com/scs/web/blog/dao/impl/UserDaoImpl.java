package com.scs.web.blog.dao.impl;

import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.util.DbUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xunmi
 * @ClassName UserDaoImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class UserDaoImpl implements UserDao {

    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findUserMobile(String mobile) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE mobile = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, mobile);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        if (rs.next()) {
            user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setPassword(rs.getString("password"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            if (rs.getDate("birthday") == null) {
                user.setBirthday(null);
            } else {
                user.setBirthday(rs.getDate("birthday").toLocalDate());
            }
            user.setIntroduction(rs.getString("introduction"));
            user.setAddress(rs.getString("address"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setStatus(rs.getShort("status"));
        }
//        DbUtil.close(rs, pstmt, connection);
        return user;
    }

    @Override
    public int[] batchInsert(List<User> userList) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_user VALUES (null,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        connection.setAutoCommit(false);
        userList.forEach(user -> {
            try {
                pstmt.setString(1, user.getMobile());
                pstmt.setString(2, user.getPassword());
                pstmt.setString(3, user.getNickname());
                pstmt.setString(4, user.getAvatar());
                pstmt.setString(5, user.getGender());
                // 日期的设置，可以使用setObject
                pstmt.setObject(6, user.getBirthday());
                pstmt.setString(7, user.getAddress());
                pstmt.setString(8, user.getIntroduction());
                pstmt.setString(9, user.getHomepage());
                pstmt.setShort(10, user.getFollows());
                pstmt.setShort(11, user.getFans());
                pstmt.setShort(12, user.getArticles());
                pstmt.setObject(13, user.getCreateTime());
                pstmt.setShort(14, user.getStatus());
                pstmt.addBatch();
            } catch (SQLException e) {
                logger.error("批量导入用户信息出错");
            }
        });
        int[] result = pstmt.executeBatch();
        // 不能忘记提交
        connection.commit();
//        DbUtil.close(null, pstmt, connection);
        return result;

    }

    @Override
    public int insert(UserDto userDto) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "INSERT INTO t_user(mobile, password, nickname, create_time) VALUES(?, ?, ?, ?) ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setString(1, userDto.getMobile());
        pstmt.setString(2, DigestUtils.md5Hex(userDto.getPassword()));
        pstmt.setString(3, userDto.getNickname());
        pstmt.setObject(4, Timestamp.valueOf(LocalDateTime.now()));
        int i = pstmt.executeUpdate();
        System.out.println("执行完插入方法后受影响的行数为：" + i);
        return i;
    }

    @Override
    public List<User> selectAll() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user ORDER BY id DESC ";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            if (rs.getDate("birthday") != null) {
                user.setBirthday(rs.getDate("birthday").toLocalDate());
            } else {
                user.setBirthday(null);
            }
            user.setAddress(rs.getString("address"));
            user.setIntroduction(rs.getString("introduction"));
            user.setHomepage(rs.getString("homepage"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setAddress(rs.getString("address"));
            user.setStatus(rs.getShort("status"));
            userList.add(user);
        }
//        DbUtil.close(rs, stmt, connection);
        return userList;
    }

    @Override
    public List<User> getHotUser() throws SQLException {
        List<User> userList = new ArrayList<>();
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user ORDER BY fans DESC LIMIT 20";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            if (rs.getDate("birthday") == null) {
                user.setBirthday(rs.getDate("birthday").toLocalDate());
            } else {
                user.setBirthday(null);
            }
            user.setAddress(rs.getString("address"));
            user.setIntroduction(rs.getString("introduction"));
            user.setHomepage(rs.getString("homepage"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setAddress(rs.getString("address"));
            user.setStatus(rs.getShort("status"));
            userList.add(user);
        }
        return userList;
    }

    @Override
    public User getUserById(Long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_user WHERE id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        User user = new User();
        while (rs.next()) {
            user.setId(rs.getLong("id"));
            user.setMobile(rs.getString("mobile"));
            user.setNickname(rs.getString("nickname"));
            user.setAvatar(rs.getString("avatar"));
            user.setGender(rs.getString("gender"));
            if (rs.getDate("birthday") != null) {
                user.setBirthday(rs.getDate("birthday").toLocalDate());
            } else {
                user.setBirthday(null);
            }
            user.setAddress(rs.getString("address"));
            user.setIntroduction(rs.getString("introduction"));
            user.setHomepage(rs.getString("homepage"));
            user.setFollows(rs.getShort("follows"));
            user.setFans(rs.getShort("fans"));
            user.setArticles(rs.getShort("articles"));
            user.setCreateTime(rs.getTimestamp("create_time").toLocalDateTime());
            user.setAddress(rs.getString("address"));
            user.setStatus(rs.getShort("status"));
        }
        return user;
    }

    @Override
    public List<Article> getArticleById(Long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
        String sql = "SELECT * FROM t_article WHERE user_id = ? ";
        PreparedStatement pstmt = connection.prepareStatement(sql);
        pstmt.setLong(1, id);
        ResultSet rs = pstmt.executeQuery();
        List<Article> articleList = new ArrayList<>();
        while (rs.next()) {
            Article article = new Article();
            article.setId(rs.getLong("id"));
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
        return articleList;
    }

    @Override
    public int countArticlesById(Long id) throws SQLException {
        Connection connection = DbUtil.getConnection();
//        String sql =
        return 0;
    }
}
