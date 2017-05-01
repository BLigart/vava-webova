<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, java.util.ArrayList, java.util.List, model.VolitelnyPredmet, model.PlanPredmet, model.Plan"%>
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
form {
    display: inline-block; 
}
</style>
</head>
<body>

<div align="left">
<form action="plany" method="post">
<input type="submit" value="Späť" style="height:30px; width:100px"/>
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
  	<th>Semester</th>
    <th>Kód</th>
    <th>Predmet</th>
    <th>Počet študentov</th>
    <th>A</th>
    <th>B</th>
    <th>C</th>
    <th>D</th>
    <th>E</th>
    <th>FX</th>
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
  	<th>Semester</th>
    <th>Kód</th>
    <th>Predmet</th>
    <th>Počet študentov</th>
    <th>A</th>
    <th>B</th>
    <th>C</th>
    <th>D</th>
    <th>E</th>
    <th>FX</th>
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


<h1>Zoznam plánov</h1>

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


<h3>Plány študentov</h3>
<table>
  <tr>
  	<th>Poradie</th>
    <th>Názov</th>
    <th>Autor</th>
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
<h3>Plány firiem</h3>
<table>
  <tr>
  	<th>Poradie</th>
    <th>Názov</th>
    <th>Autor</th>
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

<h4>Zoradenie plánov</h4>
<form action="selectsort" method="post">
<select name="sortSelect">
	<option  value="">Zvoľ faktor</option>
   	<option value="sort1">Zoradiť podľa počtu študentov, ktorí zapísali predmety v pláne(od najviac)</option>
   	<option value="sort2">Zoradiť podľa priemeru(od najlepšieho)</option>
   	<option value="sort3">Zoradiť podľa počtu FX(od najlepšieho)</option>
   	<option value="sort4">Zoradiť podľa autora</option>
</select>
<input type="submit" value="Zoradiť plány" style="height:30px; width:200px" />
</form>

<h3>Zobrazenie plánov</h3>
<form action="selectstudentplan" method="post">
<select name="studentplanSelect">
	<option  value="">Zvoľ plán</option>
   <%  for(int i = 0; i < studentPlany.size(); i++) {
	   	Plan plan = (Plan)studentPlany.get(i);
   %>
   <option value="<%=plan%>"><%=plan%></option>
   <% } %>
</select>

<input type="submit" value="Zobraziť študentský plán" style="height:30px; width:200px" />
</form>


<form action="selectfirmaplan" method="post">
<select name="firmaplanSelect">
	<option  value="">Zvoľ plán</option>
   <%  for(int i = 0; i < firmaPlany.size(); i++) {
	   	Plan plan = (Plan)firmaPlany.get(i);
   %>
   <option value="<%=plan%>"><%=plan%></option>
   <% } %>
</select>
<input type="submit" value="Zobraziť plán firmy" style="height:30px; width:200px" />
</form>

</div>

</body>
</html>