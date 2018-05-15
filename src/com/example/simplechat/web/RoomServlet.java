package com.example.simplechat.web;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.simplechat.model.ChatMessage;

@WebServlet("/room")
public class RoomServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();

		// Check whether user is already logged in
		String name = (String) session.getAttribute("name");

		if (name == null) {
			// Not logged-in.
			// Redirect back into home page.
			response.sendRedirect(contextPath + "/");
		} else {
			// Already logged-in.
			// Show chat room page
			request.getRequestDispatcher("/WEB-INF/jsp/room.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();

		// Check whether user is already logged in
		String name = (String) session.getAttribute("name");

		if (name == null) {
			// Not logged-in.
			// Redirect back into home page.
			response.sendRedirect(contextPath + "/");
			return;
		}

		// Check whether message is entered or not
		String message = request.getParameter("message");

		if (message == null || message.isEmpty()) {
			// Just reload chat room page
			response.sendRedirect(contextPath + "/room");
			return;
		}

		// Pack message info into ChatMessage object
		String ipAddress = request.getRemoteAddr();
		Date timestamp = new Date();

		ChatMessage chatMessage = new ChatMessage(name, message, ipAddress, timestamp);

		// Get the list of ChatMessage from ServletContext
		List<ChatMessage> chatMessages = (List<ChatMessage>) application.getAttribute("chatMessages");

		// Create empty ChatMessage list if empty (= when accessed for the first time)
		if (chatMessages == null) {
			chatMessages = new ArrayList<ChatMessage>();
		}

		// Insert ChatMessage into the head (1st position) of the list
		chatMessages.add(0, chatMessage);

		// Save the list of ChatMessage into ServletContext
		application.setAttribute("chatMessages", chatMessages);

		// Show chat room page
		request.getRequestDispatcher("/WEB-INF/jsp/room.jsp").forward(request, response);
	}

}
