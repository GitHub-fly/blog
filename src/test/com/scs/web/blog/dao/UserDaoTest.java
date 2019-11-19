package com.scs.web.blog.dao;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.util.JSoupSpider;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class UserDaoTest {

    private static Logger logger = LoggerFactory.getLogger(UserDaoTest.class);

    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Test
    public void batchInsert() {
        try {
            int[] result = userDao.batchInsert(JSoupSpider.getUsers());
            if(result.length != 0) {
                logger.info("批量插入信息成功(插入：" + result.length + "条信息）");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("批量新增用户出现异常");
        }
    }

    @Test
    public void insert() {
        try {
            UserDto userDto = new UserDto();
            userDto.setMobile("13457513856");
            userDto.setPassword("666666");
            userDto.setNickname("苏玉溪");
            int result = userDao.insert(userDto);
            if (result == 1) {
                logger.info("成功新增一名用户！");
            }
        } catch (SQLException e) {
            logger.error("新增一名用户失败！");
        }
    }

    @Test
    public void getHotUser() {
        try {
           List<User> userList = userDao.getHotUser();
            userList.forEach(user -> {
                System.out.println(user);
            });
        } catch (SQLException e) {
            logger.error("查询热门博主出现异常");
        }
    }

    @Test
    public void getUserById() {
        try {
            User user = userDao.getUserById(12l);
            System.out.println(user);
        } catch (SQLException e) {
            logger.error("查询指定id用户出错");
        }
    }
}