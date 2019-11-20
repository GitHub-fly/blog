package com.scs.web.blog.service;

import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserServiceTest {

    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Test
    public void signIn() {
        UserDto userDto = new UserDto("1399683628", "7ff4fc7e82abe82e2cd7a98e4fcafbeb");
        Map<String, Object> map = userService.signIn(userDto);
        System.out.println(map.get("msg"));
        System.out.println(map.get("data"));
    }

    @Test
    public void hotUser() {
        List<User> userList = userService.hotUser();
        userList.forEach(user -> {
            System.out.println(user);
        });
    }

    @Test
    public void userById() {
        List<Object> list = userService.userById(12l);
        System.out.println(list.get(0));
        System.out.println(list.get(1));
    }
}