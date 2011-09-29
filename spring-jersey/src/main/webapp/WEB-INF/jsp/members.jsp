<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
</head>
<body>
<c:forEach var="member" items="${it.members}" varStatus="s">
${member.id}:${member.name}<br/>
</c:forEach>
</form>
</body>
</html>
