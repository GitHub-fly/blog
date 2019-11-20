package com.scs.web.blog.util;

import java.util.Random;

/**
 * @author xunmi
 * @ClassName StringUtil
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 **/
public class StringUtil {

    public static String getCode() {
        int[] type = {48, 65, 97};
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        // 随机生成的字符
        int x;
        // 验证码的长度
        int size = 4;
        for (int i = 0; i < 4; i++) {
            int t = random.nextInt(3);
            switch (t) {
                case 0:
                    x = random.nextInt(10);
                    code.append((char)(x + type[t]));
                    break;
                case 1:
                case 2:
                    x = random.nextInt(26);
                    code.append((char)(x + type[t]));
                    break;
                default:
                    break;
            }
        }
        return code.toString();
    }
}
