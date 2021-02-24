<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="http://localhost:8080/shop/static/bootstrap.min.css" rel="stylesheet">
    <link href="http://localhost:8080/shop/static/ie10-viewport-bug-workaround.css" rel="stylesheet">
    <link href="http://localhost:8080/shop/static/signin.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <form id="signinForm" class="form-signin" action="http://localhost:8080/project1/user/login2" method="post" >
        <h2 class="form-signin-heading">医院信息系统(v522)</h2>
        <span th:text="${msg}" style="color: red;"></span><br/>
        <label  class="sr-only">账号</span></label>
        <input type="text" id="username" name="userName" class="form-control" placeholder="账号" required autofocus >
        <label  class="sr-only">密码</label>
        <input type="password" id="password" name="pwd" class="form-control" placeholder="密码" required >
        <input type="submit" class="btn btn-lg btn-primary btn-block" value="登录">
        <div id="divMsg" class="alert alert-danger" role="alert" style="display: none;">
            <strong id="strongMsg"></strong>
        </div>

    </form>
</div>
</body>
</html>