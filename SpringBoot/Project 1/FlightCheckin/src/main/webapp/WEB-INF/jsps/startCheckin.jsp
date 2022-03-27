<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Flight Checkin</title>
</head>
<body>
<h2>Start Checkin</h2>

<form action="startCheckin" method="POST">
	Enter the reservation ID : <input type="text" name="reservationId" />
	<input type="submit" value="Start Checkin" />
</form>
</body>
</html>