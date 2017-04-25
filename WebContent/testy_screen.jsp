<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, model.Rok, model.Predmet, model.Test, model.Otazka, java.util.ArrayList, java.util.List "%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
<style>
table, th, td {
    border: 1px solid black;
    text-align: center;
}
</style>
</head>
<body>

<%
ArrayList<Predmet> predmety = (ArrayList<Predmet>)session.getAttribute("predmety");
%>

<div align="left">
<form action="studentmainmenu" method="post">
<input type="submit" value="Späť" style="height:30px; width:100px" />
</form>
</div>
<div align="center">
<h1>Testy</h1>
<br>

<form action="selectpredmet" method="post">
<select name="predmetSelect">
	<option  value="">Zvoľ predmet</option>
   <%  for(int i = 0; i < predmety.size(); i++) {
           Predmet predmet = (Predmet)predmety.get(i);
   %>
   <option value="<%= predmet %>"><%= predmet %></option>
   <% } %>
</select>
<br>
<br><input type="submit" value="Zvoliť predmet" style="height:30px; width:100px" />
</form>

<%
	Predmet selectedPredmet = (Predmet) session.getAttribute("selectedPredmet");
	if(selectedPredmet == null) {
		selectedPredmet = new Predmet();
	}
%>
<br>
Zvolený predmet: <%=selectedPredmet %>
<br><br>

<%
List<Rok> roky = (List<Rok>)session.getAttribute("roky");
%>

<form action="selectrok" method="post">
<select name="rokSelect">
	<option  value="">Zvoľ rok</option>
   <%  for(int i = 0; i < roky.size(); i++) {
           Rok rok = roky.get(i);
   %>
   <option value="<%= rok %>"><%= rok %></option>
   <% } %>
</select>
<br>
<br><input type="submit" value="Zvoliť rok" style="height:30px; width:100px" />
</form>

<%
	Rok selectedRok = (Rok) session.getAttribute("selectedRok");
	if(selectedRok == null) {
		selectedRok = new Rok();
	}
%>
<br>
Zvolený rok: <%=selectedRok %>
<br><br>

<%
List<Test> testy = (List<Test>)session.getAttribute("testy");
if(testy == null) {
	testy = new ArrayList();
}
%>

<form action="selecttest" method="post">
<select name="testSelect">
	<option  value="">Zvoľ test</option>
   <%  for(int i = 0; i < testy.size(); i++) {
	   	Test test = (Test)testy.get(i);
   %>
   <option value="<%= test %>"><%= test %></option>
   <% } %>
</select>
<br>
<br><input type="submit" value="Zvoliť test" style="height:30px; width:100px" />
<br><br>
</form>

<form action="pridajtest" method="post">
<input type="submit" value="Pridať nový test" style="height:30px; width:150px" />
</form>

<%
	Test selectedTest = (Test) session.getAttribute("selectedTest");
	if(selectedTest == null) {
		selectedTest = new Test();
	}
%>
<br>
Zvolený test: <%=selectedTest %>
<br><br>



<%
Predmet predmet = (Predmet) request.getSession().getAttribute("selectedPredmet");
Rok rok = (Rok) request.getSession().getAttribute("selectedRok");
Test test = (Test) request.getSession().getAttribute("selectedTest");
if(predmet != null && rok != null && test != null) {
	List<Otazka> otazky = (List<Otazka>)session.getAttribute("otazky");
%>

<h1>Otázky</h1>
<br>
<table>
  <tr>
    <th>Otázka</th>
    <th>Odpoveď</th>
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
	<h1>Nová Otázka</h1>
	<h3>Otázka:</h3>
	<textarea name="otazka_text" rows="4" cols="50"></textarea><br>
	<h3>Odpoveď:</h3>
	<textarea name="odpoved_text" rows="4" cols="50"></textarea><br>
	<input type="submit" value="Pridať otázku" style="height:50px; width:200px" />
</form>
<%
}
%>




</div>

</body>
</html>