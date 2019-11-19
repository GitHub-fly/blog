package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.domain.vo.ArticleVo;
import com.scs.web.blog.entity.Article;
import com.scs.web.blog.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * @author xunmi
 * @ClassName UserDao
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public interface UserDao {
    /**
     * 批量插入用户信息
     * @param userList
     * @return
     * @throws SQLException
     */
    int[] batchInsert(List<User> userList) throws SQLException;

    /**
     * 根据手机号查找用户
     * @param mobile
     * @return
     * @throws SQLException
     */
    User findUserMobile(String mobile) throws SQLException;


    /**
     * 新曾用户
     * @param userDto
     * @return
     * @throws SQLException
     */
    int insert(UserDto userDto) throws SQLException;

    /**
     * 查询所有用户的信息
     * @return
     * @throws SQLException
     */
    List<User> selectAll() throws SQLException;

    /**
     * 获取热门博主（通过粉丝数）
     * @return
     * @throws SQLException
     */
    List<User> getHotUser() throws SQLException;

    /**
     * 通过指定id查找文章详细信息
     * @param id 指定文章id
     * @return
     * @throws SQLException
     */
    User getUserById(Long id) throws SQLException;
}
