<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, java.util.ArrayList, model.VolitelnyPredmet, model.PlanPredmet"%>
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

<div align="left">
<form action="plany" method="post">
<input type="submit" value="Späť" style="height:30px; width:100px"/>
</form>
</div>
<div align="center">
<h1>Vytvorenie nového plánu</h1>

<form action="setnazov" method="post">
	<br><input type="text" placeholder="Názov plánu" name="navozplanu" />
	<br><br><input type="submit" value="Uložiť názov" style="height:30px; width:200px" />
</form>

<br>
<%
	String nazovPlanu = (String) session.getAttribute("nazovPlanu");
	if(nazovPlanu == null) {
		nazovPlanu = "";
	}
%>
Názov plánu: <%=nazovPlanu%>

<br><br>
<form action="selectvolitelnypredmet" method="post">
<select name="semesterSelect">
	<option value="0">Zvoľ semester</option>
    <option value="1">1. semester</option>
    <option value="2">2. semester</option>
    <option value="3">3. semester</option>
    <option value="4">4. semester</option>
    <option value="5">5. semester</option>
    <option value="6">6. semester</option>
    <option value="7">7. semester</option>
    <option value="8">8. semester</option>
</select>
<br><br>

<%
ArrayList<VolitelnyPredmet> volitelne_predmety = (ArrayList<VolitelnyPredmet>)session.getAttribute("volitelne_predmety");
%>

<select name="volitelnypredmetSelect">
	<option value="">Zvoľ voliteľný predmet</option>
    <%  for(int i = 0; i < volitelne_predmety.size(); i++) {
           VolitelnyPredmet volitelny_predmet = (VolitelnyPredmet)volitelne_predmety.get(i);
    %>
    <option value="<%=volitelny_predmet%>"><%=volitelny_predmet%></option>
    <% } %>
</select>
<br><br>
<input type="submit" value="Pridať predmet do plánu" style="height:30px; width:200px" />
</form>
<br>

<%
ArrayList<PlanPredmet> predmety_v_plane = (ArrayList<PlanPredmet>)session.getAttribute("predmety_v_plane");
if(predmety_v_plane == null) {
	predmety_v_plane = new ArrayList();
}
%>

<table>
  <tr>
    <th>Semester</th>
    <th>Predmet</th>
 	
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
<input type="submit" value="Uložiť plán" style="height:30px; width:100px"/>
</form>

</div>

</body>
</html>