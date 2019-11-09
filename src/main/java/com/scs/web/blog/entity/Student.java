package com.scs.web.blog.entity;


import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author xunmi
 * @ClassName Student
 * @Description TODO
 * @Date 2019/11/6
 * @Version 1.0@
 **/
@Data
public class Student {
    private Integer id;
    private String account;
    private String password;
    private String username;
    private String avatar;
    private LocalDateTime createTime;
    private String description;
}
