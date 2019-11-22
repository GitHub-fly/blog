package com.scs.web.blog.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xunmi
 * @ClassName StringUtil
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 **/
public class StringUtil {

    /**
     * 随机获取四位长度的验证码（字母数字随机出现）
     * @return
     */
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

    /**
     * 提取字符串中的数字字符作为一个字符串数组
     * @param sourceStr
     * @return
     */
    public static String[] getDigital(String sourceStr) {
        String[] result = new String[sourceStr.length()];
        // 这里的2是指连续数字的最少个数
        Pattern p = Pattern.compile("\\d{2,}");
        Matcher m = p.matcher(sourceStr);
        int i = 0;
        while (m.find()) {
            result[i] = m.group();
            i++;
        }
        return result;
    }
}
