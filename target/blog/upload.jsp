<%--
  Created by IntelliJ IDEA.
  User: xunmi
  Date: 2019/11/21
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>图片上传页面</title>
    <style type="text/css">
        .file-box {
            width: 100px;
            height: 100px;
            border: 1px dashed burlywood;
            position: relative;
        }
        .choose-input {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            opacity: 0;
            z-index: 9999;
            cursor: pointer;
        }

        .file-box img {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
        }

        .sub-btn {
            margin-top: 50px;
            width:40px;
            height: 30px;
        }
    </style>
</head>
<body>
<form action="/api/upload" method="post" enctype="multipart/form-data">
    <div class="file-box">
        <input type="file" name="filename" multiple class="choose-input">
        <img src="https://niit-soft.oss-cn-hangzhou.aliyuncs.com/icon/_plus%20.png" alt="">
    </div>
    <input type="submit" value="上传" class="sub-btn">
</form>
<p>${msg}</p>
<p>${url}</p>
</body>
</html>
