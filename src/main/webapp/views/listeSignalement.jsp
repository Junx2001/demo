<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.example.demo.signalement.SignalementController" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
//SignalementController sc = new SignalementController();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
  <div>
      <table border="1">
        <tr>
          <th>First Name</th>
          <th>Last Name</th>
        </tr>
        <c:forEach  items="${signalements}" var ="signalement">
        <tr>
          <td>${signalement.latitude}</td>
          <td>${signalement.dateSignalement}</td>
        </tr>
        </c:forEach>
      </table>
    </div>
</body>
</html>