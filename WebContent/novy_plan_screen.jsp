<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, java.util.ArrayList, model.VolitelnyPredmet, model.PlanPredmet"%>
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
<style>
table, th, td {
    border: 1px solid black;
    text-align: center;
}
form {
    display: inline-block; 
}
</style>
</head>
<body>
<form>
            <select id="language" name="language" onchange="submit()">
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                <option value="sk" ${language == 'sk' ? 'selected' : ''}>Slovenský</option>
            </select>
        </form>
<div align="left">
<form action="plany" method="post">
<input type="submit" value="Späť" style="height:30px; width:100px"/>
</form>
</div>
<div align="center">
<h1><fmt:message key="create_new_plan" /></h1>

<%
	String nazovPlanu = (String) session.getAttribute("nazovPlanu");
	if(nazovPlanu == null) {
		nazovPlanu = "";
	}
%>
<fmt:message key="text.name_of_plan" />: <%=nazovPlanu%>

<form action="setnazov" method="post">
	<input type="text" placeholder="<fmt:message key="placeholder.add_plan_name" />" name="navozplanu" />
	<input type="submit" value="<fmt:message key="button.save_plan_name" />" style="height:30px; width:200px" />
</form>

<br><br>

<%
String savedSemester = (String)session.getAttribute("saved_semester");
%>


<form action="selectvolitelnypredmet" method="post">
<fmt:message key="text.chosen_semester" />:
<%
if(!savedSemester.equals("0")) {
%>
<%=savedSemester%>
<%
}
%>
<select name="semesterSelect">
	<option value="0"><fmt:message key="text.choose_semester" /></option>
    <option value="1"><fmt:message key="text.first_semester" /></option>
    <option value="2"><fmt:message key="text.second_semester" /></option>
    <option value="3"><fmt:message key="text.third_semester" /></option>
    <option value="4"><fmt:message key="text.fourth_semester" /></option>
    <option value="5"><fmt:message key="text.fifth_semester" /></option>
    <option value="6"><fmt:message key="text.sixth_semester" /></option>
    <option value="7"><fmt:message key="text.seventh_semester" /></option>
    <option value="8"><fmt:message key="text.eighth_semester" /></option>
</select>
<br><br>

<%
ArrayList<VolitelnyPredmet> volitelne_predmety = (ArrayList<VolitelnyPredmet>)session.getAttribute("volitelne_predmety");
%>

<select name="volitelnypredmetSelect">
	<option value=""><fmt:message key="text.choose_class" /></option>
    <%  for(int i = 0; i < volitelne_predmety.size(); i++) {
           VolitelnyPredmet volitelny_predmet = (VolitelnyPredmet)volitelne_predmety.get(i);
    %>
    <option value="<%=volitelny_predmet%>"><%=volitelny_predmet%></option>
    <% } %>
</select>
<br><br>
<input type="submit" value="<fmt:message key="button.add_class" />" style="height:30px; width:200px" />
</form>
<br><br>

<%
ArrayList<PlanPredmet> predmety_v_plane = (ArrayList<PlanPredmet>)session.getAttribute("predmety_v_plane");
if(predmety_v_plane == null) {
	predmety_v_plane = new ArrayList();
}
%>

<table>
  <tr>
    <th><fmt:message key="text.semester" /></th>
    <th><fmt:message key="text.class" /></th>
 	
 	</tr>
  	<%  for(int i = 0; i < predmety_v_plane.size(); i++) {
  		PlanPredmet planPredmet = (PlanPredmet)predmety_v_plane.get(i);
   	%>
   	<tr>
   	<td><%=planPredmet.getSemester()%></td>
  	<td><%=planPredmet.getVolitelnyPredmet()%></td>
  	</tr>
   	<% } %>
  
</table>
<br>
<form action="ulozitplan" method="post">
<input type="submit" value="<fmt:message key="button.save_plan" />" style="height:30px; width:100px"/>
</form>

</div>

</body>
</html>