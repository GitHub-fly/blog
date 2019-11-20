package com.scs.web.blog.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author xunmi
 * @ClassName ImageUtil
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 **/
public class ImageUtil {
    /**
     * 将字符串绘制成指定大小的矩形图片
     * @param content
     * @param width
     * @param height
     * @return
     */
    public static BufferedImage getImage(String content, int width, int height){
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = (Graphics2D) img.getGraphics();
        g.setColor(new Color(73, 156, 84));
        g.drawRect(0, 0, width, height);
        g.setColor(new Color(43, 43, 43));
        g.drawString(content, 100, 50);
        return img;
    }

    public static void main(String[] args) throws IOException {
        // 生成验证码
        String code = StringUtil.getCode();
        // 生成图片
        BufferedImage img = ImageUtil.getImage(code, 200, 100);
        // 将img通过字节输出流输出到指定目录
        File file = new File("E:\\图库");
        ImageIO.write(img, "jpg", file);
    }
}
