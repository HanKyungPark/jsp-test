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


<html>
<head>
    <title>JSP 게시판 웹 사이트</title>
    <meta name="viewport" content="width=device-width" , initial-scale="1">
</head>
<body>
<%
    session.invalidate();
%>
<script>
    location.href = 'main.jsp';
</script>

</body>
</html>
