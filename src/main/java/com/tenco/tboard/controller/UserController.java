package com.tenco.tboard.controller;

import java.io.IOException;

import com.tenco.tboard.repository.UserRepositoryImpl;
import com.tenco.tboard.repository.interfaces.UserRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/user/*")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private UserRepository userRepository;
	
	// UserController --> UserRepository
	
	public UserController() {
		super();
	}
	
	@Override
	public void init() throws ServletException {
		userRepository = new UserRepositoryImpl();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/signup":
			request.getRequestDispatcher("/WEB-INF/views/user/signup.jsp").forward(request, response);
			break;
		case "/signin":
			request.getRequestDispatcher("/WEB-INF/views/user/signin.jsp").forward(request, response);
			break;
		case "/logout":
			handleLogout(request, response);
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/signup":
			// TODO 회원가입 처리
			break;
		case "/signin":
			// TODO 로그인 처리
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

}
