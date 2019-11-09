package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;

import java.util.Map;

/**
 * @author xunmi
 * @ClassName UserService
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
public interface UserService {

    /**
     * 用户登录功能
     * @param userDto
     * @return
     */
    Map<String, Object> signIn(UserDto userDto);
}
