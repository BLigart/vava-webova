<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, java.util.ArrayList, java.util.List, model.VolitelnyPredmet, model.PlanPredmet, model.Plan"%>
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
<input type="submit" value="<fmt:message key="button.return" />" style="height:30px; width:100px"/>
</form>
</div>
<div align="center">


<%
Plan studentPlan = (Plan)session.getAttribute("selectedStudentPlan");
Plan firmaPlan = (Plan)session.getAttribute("selectedFirmaPlan");
if(studentPlan != null) {
%>
<h3><%=studentPlan%></h3>
<table>
  <tr>
  	<th><fmt:message key="text.semester" /></th>
    <th><fmt:message key="text.code" /></th>
    <th><fmt:message key="text.class" /></th>
    <th><fmt:message key="text.number_of_students" /></th>
    <th><fmt:message key="text.a" /></th>
    <th><fmt:message key="text.b" /></th>
    <th><fmt:message key="text.c" /></th>
    <th><fmt:message key="text.d" /></th>
    <th><fmt:message key="text.e" /></th>
    <th><fmt:message key="text.f" /></th>
  </tr>
  
  <%  for(int i = 0; i < studentPlan.getPlanPredmety().size(); i++) {
	   	PlanPredmet planPredmet = (PlanPredmet)studentPlan.getPlanPredmety().get(i);
   %>
   <tr>
   	 <td><%=planPredmet.getSemester()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getKod()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getNazov()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getPocet_studentov()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getA()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getB()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getC()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getD()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getE()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getFx()%> %</td>
   </tr>
   <% } %>
  
</table>
<%
}
else if(firmaPlan != null) {
%>
<h3><%=firmaPlan%></h3>
<table>
  <tr>
  	<th><fmt:message key="text.semester" /></th>
    <th><fmt:message key="text.code" /></th>
    <th><fmt:message key="text.class" /></th>
    <th><fmt:message key="text.number_of_students" /></th>
    <th><fmt:message key="text.a" /></th>
    <th><fmt:message key="text.b" /></th>
    <th><fmt:message key="text.c" /></th>
    <th><fmt:message key="text.d" /></th>
    <th><fmt:message key="text.e" /></th>
    <th><fmt:message key="text.f" /></th>
  </tr>
  
  <%  for(int i = 0; i < firmaPlan.getPlanPredmety().size(); i++) {
	   	PlanPredmet planPredmet = (PlanPredmet)firmaPlan.getPlanPredmety().get(i);
   %>
   <tr>
   	 <td><%=planPredmet.getSemester()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getKod()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getNazov()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getPocet_studentov()%></td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getA()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getB()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getC()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getD()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getE()%> %</td>
  	 <td><%=planPredmet.getVolitelnyPredmet().getFx()%> %</td>
   </tr>
   <% } %>
  
</table>
<%	
}
%>


<h1><fmt:message key="list_of_plans" /></h1>

<%
List<Plan> studentPlany = (List<Plan>)session.getAttribute("studentplany");
if(studentPlany == null) {
	studentPlany = new ArrayList();
}
List<Plan> firmaPlany = (List<Plan>)session.getAttribute("firmaplany");
if(firmaPlany == null) {
	firmaPlany = new ArrayList();
}
%>


<h3><fmt:message key="text.student_plans" /></h3>
<table>
  <tr>
  	<th><fmt:message key="text.order" /></th>
    <th><fmt:message key="text.name" /></th>
    <th><fmt:message key="text.author" /></th>
 </tr>
 
 <%  for(int i = 0; i < studentPlany.size(); i++) {
	   	Plan plan = (Plan)studentPlany.get(i);
   %>
   <tr>
   	 <td><%=i + 1%></td>
  	 <td><%=plan.getNazov()%></td>
  	 <td><%=plan.getCreator_name()%></td>
   </tr>
   <% } %>
 
</table>

<br>
<h3><fmt:message key="text.company_plans" /></h3>
<table>
  <tr>
  	<th><fmt:message key="text.order" /></th>
    <th><fmt:message key="text.name" /></th>
    <th><fmt:message key="text.author" /></th>
 </tr>
 
 <%  for(int i = 0; i < firmaPlany.size(); i++) {
	   	Plan plan = (Plan)firmaPlany.get(i);
   %>
   <tr>
   	 <td><%=i + 1%></td>
  	 <td><%=plan.getNazov()%></td>
  	 <td><%=plan.getCreator_name()%></td>
   </tr>
   <% } %>
 
</table>

<h4><fmt:message key="text.sorting_of_plans" /></h4>
<form action="selectsort" method="post">
<select name="sortSelect">
	<option  value=""><fmt:message key="text.choose_factor" /></option>
   	<option value="sort1"><fmt:message key="text.order_by_number_of_students" /></option>
   	<option value="sort2"><fmt:message key="text.order_by_average_grade" /></option>
   	<option value="sort3"><fmt:message key="text.order_by_number_of_fails" /></option>
   	<option value="sort4"><fmt:message key="text.order_by_author" /></option>
</select>
<input type="submit" value="<fmt:message key="button.sort_plans" />" style="height:30px; width:200px" />
</form>

<h3><fmt:message key="text.show_plans" /></h3>
<form action="selectstudentplan" method="post">
<select name="studentplanSelect">
	<option  value=""><fmt:message key="text.choose_plan" /></option>
   <%  for(int i = 0; i < studentPlany.size(); i++) {
	   	Plan plan = (Plan)studentPlany.get(i);
   %>
   <option value="<%=plan%>"><%=plan%></option>
   <% } %>
</select>

<input type="submit" value="<fmt:message key="button.choose_student_plan" />" style="height:30px; width:200px" />
</form>


<form action="selectfirmaplan" method="post">
<select name="firmaplanSelect">
	<option  value=""><fmt:message key="text.choose_plan" /></option>
   <%  for(int i = 0; i < firmaPlany.size(); i++) {
	   	Plan plan = (Plan)firmaPlany.get(i);
   %>
   <option value="<%=plan%>"><%=plan%></option>
   <% } %>
</select>
<input type="submit" value="<fmt:message key="button.choose_company_plan" />" style="height:30px; width:200px" />
</form>

</div>

</body>
</html>