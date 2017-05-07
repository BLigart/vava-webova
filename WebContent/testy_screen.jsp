<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, model.Rok, model.Predmet, model.Test, model.Otazka, java.util.ArrayList, java.util.List "%>
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
<%
ArrayList<Predmet> predmety = (ArrayList<Predmet>)session.getAttribute("predmety");
%>

<div align="left">
<form action="studentmainmenu" method="post">
<input type="submit" value="<fmt:message key="button.return" />" style="height:30px; width:100px" />
</form>
</div>
<div align="center">
<h1><fmt:message key="tests" /></h1>
<br>



<%
Predmet predmet = (Predmet) request.getSession().getAttribute("selectedPredmet");
Rok rok = (Rok) request.getSession().getAttribute("selectedRok");
Test test = (Test) request.getSession().getAttribute("selectedTest");
if(predmet != null && rok != null && test != null) {
	List<Otazka> otazky = (List<Otazka>)session.getAttribute("otazky");
%>

<h3><%=test%> z predmetu <%=predmet.getNazov()%>, rok <%=rok%></h3>
<br>
<table>
  <tr>
    <th><fmt:message key="text.question" /></th>
    <th><fmt:message key="text.answer" /></th>
  </tr>
  
  <%  for(int i = 0; i < otazky.size(); i++) {
	   	Otazka otazka = (Otazka)otazky.get(i);
   %>
   <tr>
   	 <td><%=otazka.getOtazka()%></td>
  	 <td><%=otazka.getOdpoved()%></td>
   </tr>
   <% } %>
  
</table>
<br>
<form action="pridajotazku" method="post">
	<h1><fmt:message key="text.new_question" /></h1>
	<h3><fmt:message key="text.question" />":</h3>
	<textarea name="otazka_text" rows="4" cols="50"></textarea><br>
	<h3><fmt:message key="text.answer" />":</h3>
	<textarea name="odpoved_text" rows="4" cols="50"></textarea><br>
	<input type="submit" value= "<fmt:message key="button.add_question" />" style="height:50px; width:200px" />
</form>
<%
}
%>




<%
	Predmet selectedPredmet = (Predmet) session.getAttribute("selectedPredmet");
	if(selectedPredmet == null) {
		selectedPredmet = new Predmet();
	}
%>

<form action="selectpredmet" method="post">
<fmt:message key="text.chosen_class" />: <%=selectedPredmet %>
<select name="predmetSelect">
	<option  value=""><fmt:message key="choose_class" /></option>
   <%  for(int i = 0; i < predmety.size(); i++) {
           predmet = (Predmet)predmety.get(i);
   %>
   <option value="<%= predmet %>"><%= predmet %></option>
   <% } %>
</select>
<input type="submit" value="<fmt:message key="choose_class" />" style="height:30px; width:100px" />
</form>
<br><br>

<%
List<Rok> roky = (List<Rok>)session.getAttribute("roky");
Rok selectedRok = (Rok) session.getAttribute("selectedRok");
if(selectedRok == null) {
	selectedRok = new Rok();
}
%>

<form action="selectrok" method="post">
<fmt:message key="text.chosen_year" />: <%=selectedRok %>
<select name="rokSelect">
	<option  value=""><fmt:message key="text.choose_year" /></option>
   <%  for(int i = 0; i < roky.size(); i++) {
           rok = roky.get(i);
   %>
   <option value="<%= rok %>"><%= rok %></option>
   <% } %>
</select>
<input type="submit" value="<fmt:message key="button.choose_year" />" style="height:30px; width:100px" />
</form>
<br><br>

<%
List<Test> testy = (List<Test>)session.getAttribute("testy");
if(testy == null) {
	testy = new ArrayList();
}
Test selectedTest = (Test) session.getAttribute("selectedTest");
if(selectedTest == null) {
	selectedTest = new Test();
}
%>

<form action="selecttest" method="post">
<fmt:message key="text.chosen_test" />: <%=selectedTest %>
<select name="testSelect">
	<option  value=""><fmt:message key="text.choose_test" /></option>
   <%  for(int i = 0; i < testy.size(); i++) {
	   	test = (Test)testy.get(i);
   %>
   <option value="<%= test %>"><%= test %></option>
   <% } %>
</select>
<input type="submit" value="<fmt:message key="button.choose_year" />" style="height:30px; width:100px" />
</form>

<form action="pridajtest" method="post">
<input type="submit" value="<fmt:message key="button.add_test" />" style="height:30px; width:150px" />
</form>

</div>

</body>
</html>