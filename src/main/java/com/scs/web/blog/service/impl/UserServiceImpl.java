package com.scs.web.blog.service.impl;

import com.scs.web.blog.dao.UserDao;
import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xunmi
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDao userDao = DaoFactory.getUserDaoInstance();

    @Override
    public Map<String, Object> signIn(UserDto userDto) {
        User user = null;
        Map<String, Object> map = new HashMap<>();
        try {
            user = userDao.findUserMobile(userDto.getMobile());
        } catch (SQLException e) {
            logger.error("根据手机号查询用户出现异常");
        }
        if (user != null) {
            if (user.getPassword().equals(userDto.getPassword())) {
                map.put("msg", Message.SIGN_IN_SUCCESS);
                map.put("data", user);
                logger.info("手机号为：" + userDto.getMobile() + "的用户登录成功");
            } else {
                map.put("msg", Message.PASSWORD_ERROR);
                logger.info("手机号为：" + userDto.getMobile() + "的用户登录时密码出现一次错误");
            }
        } else {
            map.put("msg", Message.MOBILE_NOT_FOUND);
        }
        return map;
    }
}
