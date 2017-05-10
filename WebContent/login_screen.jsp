<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="main.resources.text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login page</title>
</head>
<body>
<form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="sk" ${language == 'sk' ? 'selected' : ''}>Slovenský</option>
            </select>
        </form>
<div align="center">
<h1>StudentManager FIIT STU</h1>

<form action="login" method="post">
	<br><input type="text" placeholder="<fmt:message key="placeholder.username" />" name="username" style="width:130px" />
	<br><br><input type="password" placeholder="<fmt:message key="placeholder.password" />" name="password" />
	<br><br><input type="submit" value="<fmt:message key="button.login" />" style="height:50px; width:200px" />
</form>
</div>

</body>
</html>