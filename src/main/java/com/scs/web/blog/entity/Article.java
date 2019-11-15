package com.scs.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xunmi
 * @ClassName ArticleService
 * @Description TODO
 * @Date 2019/11/9
 * @Version 1.0
 **/

@Data
public class Article {

    private Long id;
    private Long userId;
    private String title;
    private String content;
    private String cover;
    private Integer diamond;
    private Integer comments;
    private Integer likes;
    private LocalDateTime publishTime;

}
