package com.scs.web.blog.service;

import com.scs.web.blog.entity.Topic;

import java.util.List;

/**
 * @author xunmi
 * @ClassName TopicService
 * @Description 专题业务逻辑层
 * @Date 2019/11/22
 * @Version 1.0
 **/
public interface TopicService {

    /**
     * 获取推荐专题（通过发表的文章数来决定）
     * @return
     */
    List<Topic> hotTopic();

}
