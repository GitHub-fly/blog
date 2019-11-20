package com.scs.web.blog.controller;

import com.scs.web.blog.util.ImageUtil;
import com.scs.web.blog.util.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author xunmi
 * @ClassName CodeColltroller
 * @Description TODO
 * @Date 2019/11/20
 * @Version 1.0
 **/
@WebServlet(urlPatterns = "/code")
public class CodeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 生成验证码
        String code = StringUtil.getCode();
        // 生成图片
        BufferedImage img = ImageUtil.getImage(code, 200, 100);
        // 设置resp的contentType类型(响应类型)
        resp.setContentType("image/jpg");
        // 将图片通过输出流，返回给客户端
        OutputStream out = resp.getOutputStream();
        ImageIO.write(img, "jpg", out);
        out.close();
    }
}
