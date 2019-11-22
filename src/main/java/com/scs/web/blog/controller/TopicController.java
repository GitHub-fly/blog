package com.scs.web.blog.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.factory.ServiceFactory;
import com.scs.web.blog.service.TopicService;
import com.scs.web.blog.util.ResponseObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * @author xunmi
 * @ClassName TopicController
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/api/topic/hot")
public class TopicController extends HttpServlet {
    private static Logger logger = LoggerFactory.getLogger(TopicController.class);
    private TopicService topicService = ServiceFactory.getTopicServiceInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestPath = req.getRequestURI();
        ResponseObject ro = null;
        List<Topic> topicList = null;
        switch (requestPath) {
            case "/api/topic/hot":
                topicList = topicService.hotTopic();
                ro = ResponseObject.success(resp.getStatus(), resp.getStatus() == 200 ? "成功" : "失败", topicList);
                break;
        }
        PrintWriter out = resp.getWriter();
        Gson gson = new GsonBuilder().create();
        out.print(gson.toJson(ro));
        out.close();
    }
}
