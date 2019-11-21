package com.scs.web.blog.util;

import com.scs.web.blog.entity.User;
import com.scs.web.blog.factory.DaoFactory;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int userIdMax = list.size();
        Random random = new Random();
        return random.nextInt(userIdMax) + 1;
    }

    /**
     * 获取一串唯一的编码，用作上传文件的文件名（不会出现重复）
     * 以时间2019-11-21T15:19:57.285367300 为依据进行获取
     * @return
     */
    public static String getUUID() {
        String localDateTime = LocalDateTime.now().toString();
        String[] localDateTimeArr = localDateTime.split("T");
        // 取出年月日
        String date = localDateTimeArr[0];
        // 取出时分秒
        String time = localDateTimeArr[1];
        // 获取年月日组成的字符串数字
        String[] dateArr = date.split("-");
        // 获取时分秒组成的字符串数字
        String[] timeArr = time.split(":");
        // 处理时分秒中的秒，获取4位即可
        String second = timeArr[2].substring(0, 5).replace(".", "");
        timeArr[2] = second;
        // 分别将年月日、时分秒数组转换为字符串
        StringBuilder uuid = new StringBuilder();
        for(int i = 0, j = 0; i < 6; i++) {
            if (i < 3) {
                uuid.append(dateArr[i]);
            } else {
                uuid.append(timeArr[j]);
                j++;
            }
        }
        return uuid.toString();
    }

    /**
     * 通过UUID类获取某文件的随机“加密”名
     * @param fileName
     * @return
     */
    public static String getRandomName(String fileName) {
        int index = fileName.lastIndexOf(".");
        // 获取文件后缀
        String type = fileName.substring(index);
        String name = UUID.randomUUID().toString().replace("-", "").substring(0, 16) + type;
        return name;
    }
}
