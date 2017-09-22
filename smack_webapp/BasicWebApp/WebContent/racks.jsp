<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DB connection</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>slot0</th>
        <th>slot1</th>
        <th>slot2</th>
        <th>slot3</th>
    </tr>
    <c:forEach items="${racksData}" var="rack">
        <tr>
            <td><c:out value="${rack.rackID}" /></td>
            <td><c:out value="${rack.slot0}" /></td>
            <td><c:out value="${rack.slot1}" /></td>
            <td><c:out value="${rack.slot2}" /></td>
            <td><c:out value="${rack.slot3}" /></td>
        </tr>
    </c:forEach>
</table>
<!--<c:out value="${racksData}" />-->
<form action="racks" method="post">
            <p>
                <label for="id">Enter rack ID:</label>
                <input id="id" name="id" value="${fn:escapeXml(param.id)}">
                <span class="error">${messages.id}</span>
            </p>
            <p>
                <label for="slot">Enter slot number:</label>
                <input id="slot" name="slot" value="${fn:escapeXml(param.slot)}">
                <span class="error">${messages.slot}</span>
            </p>
            <p>
                <label for="status">Enter new status:</label>
                <input id="status" name="status" value="${fn:escapeXml(param.status)}">
                <span class="error">${messages.status}</span>
            </p>
            <p>
                <input type="submit">
                <span class="success">${messages.success}</span>
            </p>
        </form>
</body>
</html>