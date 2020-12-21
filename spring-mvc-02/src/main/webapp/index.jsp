<%--
  Created by IntelliJ IDEA.
  User: 25118
  Date: 2020/12/5
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>文件上传</h3>
<a href="model">入门程序</a>
<form action="/file/upload2" method="post" enctype="multipart/form-data">
文件上传<input type="file" name="file"/><br/>
<input type="submit" value="上传">
</form>

<a href="exception">异常测试</a><br>
<a href="/v1/interceptor">拦截器</a>
</body>
</html>
