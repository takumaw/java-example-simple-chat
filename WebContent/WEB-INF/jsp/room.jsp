<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.example.simplechat.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Chat</title>
</head>
<body>
<h1>Simple Chat</h1>

<h2>Room</h2>
Welcome, <%= session.getAttribute("name") %>.<br>
<a href="logout">Logout</a>
<hr>

<form action="room" method="post">
<input type="text" name="message" autofocus>
<input type="submit" name="Send">
</form>
<hr>

<table width="100%" border="1px">
<tbody>
<tr>
<th width="150px">Name</th>
<th width="200px">Posted At</th>
<th>Message</th>
</tr>

<%
List<ChatMessage> chatMessages = (List<ChatMessage>) application.getAttribute("chatMessages");

if (chatMessages != null) {
	for (ChatMessage chatMessage: chatMessages) {
		out.println("<tr>");
		
		out.println("<td>");
		out.println(chatMessage.getName());
		out.println("</td>");
		
		out.println("<td>");
		Date timestamp = chatMessage.getTimestamp();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Tokyo"));
		String timestampString = dateFormat.format(timestamp);
		out.println(timestampString);
		out.println("</td>");
		
		out.println("<td>");
		out.println(chatMessage.getMessage());
		out.println("</td>");
		
		out.println("<tr>");
	}
}
%>
</tbody>
</table>
</body>
</html>