<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
</head>
<body>
<form action="/post/submit" method="post">
id:<input type="text" name="id"/>
password:<input type="password" name="password"/>
<input type="submit"/>
</form>
</body>
</html>
