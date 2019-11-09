package com.scs.web.blog.factory;

import com.scs.web.blog.service.UserService;
import com.scs.web.blog.service.impl.UserServiceImpl;

/**
 * @author xunmi
 * @ClassName ServiceFactory
 * @Description TODO
 * @Date 2019/11/7
 * @Version 1.0
 **/
public class ServiceFactory {

    public static UserService getUserServiceInstance() {

        return new UserServiceImpl();
    }
}
