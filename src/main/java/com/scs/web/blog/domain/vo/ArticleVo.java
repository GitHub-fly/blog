package com.scs.web.blog.domain.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xunmi
 * @ClassName ArticleVo
 * @Description TODO
 * @Date 2019/11/16
 * @Version 1.0
 **/
@Data
public class ArticleVo {
    private Long id;
    private Long userId;
    private String nickname;
    private String title;
    private String content;
    private String cover;
    private Integer diamond;
    private Integer comments;
    private Integer likes;
    private LocalDateTime publishTime;
}
