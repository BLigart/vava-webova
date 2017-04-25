<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.User"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Success</title>
</head>
<body>

<div align="left">
<form action="logout" method="post">
<input type="submit" value="Odhlásiť sa" style="height:30px; width:100px"/>
</form>
</div>
<div align="center">
<h1>Hlavné menu</h1>

<form action="plany" method="post">
<input type="submit" value="Plány výberu predmetov" style="height:50px; width:200px" />
</form>
<br>
<form action="testy" method="post">
<input type="submit" value="Testy" style="height:50px; width:200px" />
</form>
</div>

</body>
</html>