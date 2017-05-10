<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, model.Firma"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="main.resources.text" />
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
<%
	Firma firma = (Firma) session.getAttribute("firma");
	if(firma != null) {
%>	
<div align="left">
<form action="logout" method="post">
<input type="submit" value="<fmt:message key="button.logout" />" style="height:30px; width:100px"/>
</form>
</div>
<%	
	}
	else {
%>
<div align="left">
<form action="studentmainmenu" method="post">
<input type="submit" value="<fmt:message key="button.return" />" style="height:30px; width:100px"/>
</form>
</div>
<%
	}
%>
<div align="center">
<h1><fmt:message key="class_choice_plans" /></h1>

<form action="vytvorenieplanu" method="post">
<input type="submit" value="<fmt:message key="create_new_plan" />" style="height:50px; width:300px" />
</form>
<br>
<form action="zmazanieplanu" method="post">
<input type="submit" value="<fmt:message key="button.delete_plan" />" style="height:50px; width:300px" />
</form>
<br>
<form action="zoznamplanov" method="post">
<input type="submit" value="<fmt:message key="list_of_plans" />" style="height:50px; width:300px" />
</form>
<br>
<%
if(firma == null) {
%>
<form action="aktualizacia" method="post">
<input type="submit" value="<fmt:message key="button.refresh_classes" />" style="height:50px; width:300px" />
</form>
<br>
<%
}
%>

</div>

</body>
</html>