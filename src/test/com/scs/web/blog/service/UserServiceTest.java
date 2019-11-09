package com.scs.web.blog.service;

import com.scs.web.blog.domain.UserDto;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class UserServiceTest {

    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        UserDto userDto = new UserDto("1399683628", "7ff4fc7e82abe82e2cd7a98e4fcafbeb");
        Map<String, Object> map = userService.signIn(userDto);
        System.out.println(map.get("msg"));
        System.out.println(map.get("data"));
    }
}