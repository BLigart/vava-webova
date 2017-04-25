<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, model.Plan, java.util.List, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zmazanie plánu</title>
</head>
<body>

<div align="left">
<form action=plany method="post">
<input type="submit" value="Späť" style="height:30px; width:100px"/>
</form>
</div>
<div align="center">
<h1>Zmazanie plánu</h1>

<%
List<Plan> plany = (List<Plan>)session.getAttribute("plany");
if(plany == null) {
	plany = new ArrayList();
}
%>

<form action="zmazatplan" method="post">
<select name="planSelect">
	<option  value="">Zvoľ plán</option>
   <%  for(int i = 0; i < plany.size(); i++) {
           Plan plan = (Plan)plany.get(i);
   %>
   <option value="<%=plan%>"><%=plan%></option>
   <% } %>
</select>
<br><br>
<input type="submit" value="Zmazať plán" style="height:50px; width:200px" />
</form>
</div>

</body>
</html>