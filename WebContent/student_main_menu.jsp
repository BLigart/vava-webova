<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="configs.resources.text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
</head>
<body>
<form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="sk" ${language == 'sk' ? 'selected' : ''}>Slovenský</option>
            </select>
        </form>
<div align="left">
<form action="logout" method="post">
<input type="submit" value= "<fmt:message key="button.logout" />" style="height:30px; width:100px"/>
</form>
</div>
<div align="center">
<h1><fmt:message key="text.main_menu" /></h1>

<form action="plany" method="post">
<input type="submit" value="<fmt:message key="class_choice_plans" />" style="height:50px; width:200px" />
</form>
<br>
<form action="testy" method="post">
<input type="submit" value="<fmt:message key="tests" />" style="height:50px; width:200px" />
</form>
</div>

</body>
</html>