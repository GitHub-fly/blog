package com.scs.web.blog.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xunmi
 * @ClassName Topic
 * @Description TODO
 * @Date 2019/11/22
 * @Version 1.0
 **/
@Data
public class Topic {

    private Long id;
    /**
     * 管理员id
     */
    private Long adminId;
    private String topicName;
    private String logo;
    private String description;
    private Integer articles;
    private Integer follows;
    private LocalDateTime createTime;

}
