<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, model.Plan, java.util.List, java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="main.resources.text" />
<!DOCTYPE html>
<html lang="${language}">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>"<fmt:message key="text.delete_plan" />"</title>
</head>
<body>
<form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="sk" ${language == 'sk' ? 'selected' : ''}>Slovenský</option>
            </select>
        </form>
<div align="left">
<form action=plany method="post">
<input type="submit" value="<fmt:message key="button.return" />" style="height:30px; width:100px"/>
</form>
</div>
<div align="center">
<h1><fmt:message key="text.delete_plan" /></h1>

<%
List<Plan> plany = (List<Plan>)session.getAttribute("plany");
if(plany == null) {
	plany = new ArrayList();
}
%>

<form action="zmazatplan" method="post">
<select name="planSelect">
	<option  value=""><fmt:message key="text.choose_plan" /></option>
   <%  for(int i = 0; i < plany.size(); i++) {
           Plan plan = (Plan)plany.get(i);
   %>
   <option value="<%=plan%>"><%=plan%></option>
   <% } %>
</select>
<br><br>
<input type="submit" value="<fmt:message key="button.delete_plan" />" style="height:50px; width:200px" />
</form>
</div>

</body>
</html>