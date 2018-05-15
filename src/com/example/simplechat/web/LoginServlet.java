package com.example.simplechat.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String contextPath = request.getContextPath();

		// Get user name from HTTP parameter.
		String name = (String) request.getParameter("name");

		// Check whether user name is entered or not.
		if (name == null || name.isEmpty()) {
			// Name not entered
			// Set error message and show error page
			request.setAttribute("errorMessage", "Please enter your name.");
			request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
		} else {
			// Name entered
			// Store name into HttpSession and show chat room page
			session.setAttribute("name", name);

			response.sendRedirect(contextPath + "/room");
		}
	}

}
