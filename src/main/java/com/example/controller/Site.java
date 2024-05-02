package com.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.entity.User;
import com.example.model.UserModel;

@WebServlet("/site")
public class Site extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		
		switch (page) {

		case "listusers": {
			listUsers(request, response);
			break;
		}
		case "adduser": {
			addUser(request, response);
			break;
		}
		case "updateuser": {
			System.out.println(request.getParameter("page"));
			updateUser(request, response);
			break;
		}
		
		case "deleteuser": {
			new UserModel().deleteUser(Integer.parseInt(request.getParameter("user_id")));
			listUsers(request, response);
			break;
		}
		
		default: {
			request.setAttribute("title", "Error Page");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			break;
		}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String form = request.getParameter("form");
		switch (form) {

		case "addUserOperation": {
			User user = new User(request.getParameter("username"),request.getParameter("email"));
			new UserModel().addUser(user);
			listUsers(request, response);
			break;
		}
		
		case "updateUserOperation": {
			User updtaedUser = new User(Integer.parseInt(request.getParameter("user_id")),request.getParameter("username"),request.getParameter("email"));
			new UserModel().updateUser(updtaedUser);;
			listUsers(request, response);
			break;
		}
		
		default: {
			request.setAttribute("title", "Error Page");
			request.getRequestDispatcher("error.jsp").forward(request, response);
			break;
		}
		}
	}
	
	protected void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = new ArrayList<>();
		users = new UserModel().listUsers();
		request.setAttribute("listusers", users);
		request.setAttribute("title", "List Users");
		request.getRequestDispatcher("listusers.jsp").forward(request, response);
	}
	
	protected void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Add User");
		request.getRequestDispatcher("adduser.jsp").forward(request, response);
	}
	
	protected void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("title", "Update User");
		request.getRequestDispatcher("updateuser.jsp").forward(request, response);
	}

}
