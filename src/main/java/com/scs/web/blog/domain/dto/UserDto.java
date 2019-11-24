package com.scs.web.blog.domain.dto;

import lombok.Data;

/**
 * @author xunmi
 * @ClassName UserDto
 * @Description 用户传输对象
 * @Date 2019/11/9
 * @Version 1.0
 **/

@Data
public class UserDto {
    private String mobile;
    private String password;
    private String nickname;
    private String code;

    public UserDto(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    public UserDto(String mobile, String password, String nickname) {
        this.mobile = mobile;
        this.password = password;
        this.nickname = nickname;
    }

    public UserDto() {

    }
}
