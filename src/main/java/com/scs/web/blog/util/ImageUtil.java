package com.scs.web.blog.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;

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
        // 画矩形
        g.drawRect(0, 0, width, height);
        // 填充背景颜色
        g.setColor(new Color(193, 193, 193));
        g.fillRect(0, 0 , width, height);
        // 写字
        g.setFont(new Font("微软雅黑", Font.BOLD, 25));
        g.setColor(new Color(43, 43, 43));
        g.drawString(content, (width-90)/2, height-10);
        // 画线条
        g.setColor(new Color(124, 164, 156));
        g.drawLine(0, 10, 200, 10);
        g.drawLine(0, 20, 200, 20);
        g.drawLine(0, 30, 200, 30);
        g.drawLine(0, 40, 200, 40);
        g.setColor(new Color(152, 118, 170));
        g.drawLine(20, 30, 80, 5);
        g.drawLine(20, 30, 180, 30);
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
