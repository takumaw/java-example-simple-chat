package com.example.simplechat.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.simplechat.model.ChatMessage;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext application = request.getServletContext();

		// Get context path
		// If your URL is "http://localhost:8080/CONTEXTPATH/URL_PATTERN",
		// then contextPath will be:
		// contextPath = "/CONTEXTPATH"
		// e.g. contextPath = "/SimpleChat"
		String contextPath = request.getContextPath();

		// Get name from HttpSession
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");

		// Check if user is already logged in
		if (name != null) {
			// Already logged-in
			// Show chat room page.
			response.sendRedirect(contextPath + "/room");
		} else {
			// Not logged-in.
			// Show home page.
			request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
		}
	}

}
