<%--
  Created by IntelliJ IDEA.
  User: hankyung
  Date: 3/31/25
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="user.UserDao" %>
<%@ page import="java.io.PrintWriter" %>

<jsp:useBean id="user" class="user.User" scope="page"/>
<jsp:setProperty name="user" property="userID"/>
<jsp:setProperty name="user" property="userPassword"/>

<html>
<head>
    <title>JSP 게시판 웹 사이트</title>
    <meta name="viewport" content="width=device-width" , initial-scale="1">
</head>
<body>
<%
    String userID = null;
    if (session.getAttribute("userID") != null) {
        userID = (String) session.getAttribute("userID");
    }
    if (userID != null) {
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('이미 로그인이 되어있습니다.')");
        script.println("location.href = 'main.jsp'");
        script.println("</script>");
    }
    UserDao userDao = new UserDao();
    int result = userDao.login(user.getUserID(), user.getUserPassword());
    if (result == 1) {
        session.setAttribute("userID",user.getUserID());
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("location.href = 'main.jsp'");
        script.println("</script>");
    }
    else if (result == 0){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('비밀번호가 틀립니다.')");
        script.println("history.back()");
        script.println("</script>");
    }else if (result == -1){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('존재하지 않는 아이디입니다.')");
        script.println("history.back()");
        script.println("</script>");
    }else if (result == 2){
        PrintWriter script = response.getWriter();
        script.println("<script>");
        script.println("alert('데이터베이스 오류가 발생했습니다.')");
        script.println("history.back()");
        script.println("</script>");
    }

%>

</body>
</html>
