<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User, model.Firma"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
</head>
<body>

<%
	Firma firma = (Firma) session.getAttribute("firma");
	if(firma != null) {
%>	
<div align="left">
<form action="logout" method="post">
<input type="submit" value="Odhlásiť sa" style="height:30px; width:100px"/>
</form>
</div>
<%	
	}
	else {
%>
<div align="left">
<form action="studentmainmenu" method="post">
<input type="submit" value="Späť" style="height:30px; width:100px"/>
</form>
</div>
<%
	}
%>
<div align="center">
<h1>Plány výberu predmetov</h1>

<form action="vytvorenieplanu" method="post">
<input type="submit" value="Vytvoriť nový plán" style="height:50px; width:300px" />
</form>
<br>
<form action="zmazanieplanu" method="post">
<input type="submit" value="Zmazať plán" style="height:50px; width:300px" />
</form>
<br>
<form action="zoznamplanov" method="post">
<input type="submit" value="Zoznam plánov" style="height:50px; width:300px" />
</form>
<br>
<%
if(firma == null) {
%>
<form action="aktualizacia" method="post">
<input type="submit" value="Aktualizácia volitelných predmetov z AISu" style="height:50px; width:300px" />
</form>
<br>
<%
}
%>

</div>

</body>
</html>