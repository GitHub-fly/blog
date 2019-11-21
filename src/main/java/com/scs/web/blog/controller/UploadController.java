package com.scs.web.blog.controller;

import com.scs.web.blog.util.DataUtil;
import com.scs.web.blog.util.FileUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

/**
 * @author xunmi
 * @ClassName UploadController
 * @Description TODO
 * @Date 2019/11/21
 * @Version 1.0
 **/
@MultipartConfig(maxFileSize = 1024 * 1024 * 50)
@WebServlet(urlPatterns = "/api/upload")
public class UploadController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 取出所选文件的part
        Collection<Part> parts = req.getParts();

        for (Part part : parts) {
            long max = 1024 * 1024 * 2;
            if (part.getSize() < max) {
                // 取出文件名称
                String name = part.getSubmittedFileName();
                // 重新构造选所文件的名字
                String namePlus = DataUtil.getRandomName(name);
                // 获取根目录路径"F:\IDEASpace\blog\target\blog"
                String path = req.getSession().getServletContext().getRealPath("");
                // 获取上传日期
                LocalDate date = LocalDate.now();
                // 构造上传路径
                String p = FileUtil.createFile(path + date);
                // 写入电脑磁盘
                part.write(p + "\\" + namePlus);
                req.setAttribute("msg", "上传成功");
            } else {
                req.setAttribute("msg", "上传失败");
            }
        }
        resp.setContentType("image/jpg");
        req.getRequestDispatcher("/upload.jsp").forward(req, resp);
    }
}
