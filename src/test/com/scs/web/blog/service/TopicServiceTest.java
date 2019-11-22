package com.scs.web.blog.service;

import com.scs.web.blog.entity.Topic;
import com.scs.web.blog.factory.ServiceFactory;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TopicServiceTest {

    private TopicService topicService = ServiceFactory.getTopicServiceInstance();

    @Test
    public void hotTopic() {
        List<Topic> list = topicService.hotTopic();
        list.forEach(System.out::println);
    }
}