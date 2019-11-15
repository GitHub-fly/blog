package com.scs.web.blog.util;

import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author xunmi
 * @ClassName DataUtil
 * @Description 数据生成工具，用来生成用户的一些数据
 * @Date 2019/11/9
 * @Version 1.0
 **/
public class  DataUtil {

    /**
     * 获得电话号码
     *
     * @return
     */
    public static String getMobile() {
        StringBuilder mobile = new StringBuilder("139");
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int num = random.nextInt(10);
            mobile.append(num);
        }
        return mobile.toString();
    }

    /**
     * 获取随机密码
     * @return
     */
    public static String getPassword() {
        StringBuilder password = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 8; i++) {
            int num = random.nextInt(10);
            password.append(num);
        }
        return DigestUtils.md5Hex(password.toString());
    }

    /**
     * 获取随机性别
     * @return
     */
    public static String getGender() {
        String[] genders = new String[]{"男", "女"};
        Random random = new Random();
        int index = random.nextInt(2);
        return genders[index];
    }

    /**
     * 获取随机生日数据
     * @return
     */
    public static LocalDate getBirthday() {
        LocalDate now = LocalDate.now();
        Random random = new Random();
        int bound = random.nextInt(8888);
        return now.minusDays(bound);
    }

    /**
     * 获取随机作者id
     * @return
     */
    public static int getUserId() {
        List<User> list = new ArrayList<>();
        try {
             list = DaoFactory.getUserDaoInstance().selectAll();
            System.out.println("sdasd");
            System.out.println(list.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int userIdMax = list.size();
        Random random = new Random();
        return random.nextInt(userIdMax) + 1;
    }
}
