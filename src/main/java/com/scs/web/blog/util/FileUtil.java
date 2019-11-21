package com.scs.web.blog.util;

import java.io.File;


/**
 * @author xunmi
 * @ClassName FileUtil
 * @Description TODO
 * @Date 2019/11/21
 * @Version 1.0
 **/
public class FileUtil {

    /**
     * 创建指定     “文件夹”
     *
     * @param path
     * @return
     */
    public static String createFile(String path) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.mkdir();
            }
        } catch (Exception e) {
            System.out.println("创建文件出现异常");
        }
        return file.toString();
    }

}
