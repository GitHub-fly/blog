package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.domain.dto.UserDto;
import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.UserService;
import com.scs.web.blog.util.Message;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xunmi
 * @ClassName UserController
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/
@WebServlet(urlPatterns = {"/api/sign-in", "/api/register", "/api/user/hot", "/api/user/detail/*"})
public class UserController extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService = ServiceFactory.getUserServiceInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        Gson gson = new GsonBuilder().create();
        UserDto userDto = gson.fromJson(stringBuilder.toString(), UserDto.class);
        Map<String, Object> map = null;
        // 获取请求路径
        String requestPath = req.getRequestURI().trim();
        switch (requestPath) {
            case "/api/sign-in":
                map = userService.signIn(userDto);
                break;
            case "/api/register":
                map = userService.register(userDto);
                break;
        }
        String msg = (String) map.get("msg");
        ResponseObject ro;
        switch (msg) {
            case Message.SIGN_IN_SUCCESS:
            case Message.REGISTER_SUCCESS:
                ro = ResponseObject.success(200, msg, map.get("data"));
                break;
            case Message.PASSWORD_ERROR:
            case Message.MOBILE_NOT_FOUND:
            case Message.REGISTER_DEFEATED:
            default:
                ro = ResponseObject.success(200, msg);
        }
        PrintWriter out = resp.getWriter();
        out.print(gson.toJson(ro));
        out.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> userList = new ArrayList<>();
        String requestPath = req.getRequestURI().trim();
        ResponseObject ro = null;
        List<Object> list = null;
        switch (requestPath) {
            case "/api/user/hot":
                userList = userService.hotUser();
                ro = ResponseObject.success(resp.getStatus(), resp.getStatus() == 200 ? "成功" : "失败", userList);
                break;
            default:
                String id = requestPath.substring(requestPath.lastIndexOf("/") + 1);
                list = userService.userById(Long.valueOf(id));
                ro = ResponseObject.success(resp.getStatus(), resp.getStatus() == 200 ? "成功" : "失败", list);
        }
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(ro));
        out.close();
    }

    @Override
    public void init() throws ServletException {
        logger.info("UserController初始化");
    }
}
