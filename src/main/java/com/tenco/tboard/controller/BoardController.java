package com.tenco.tboard.controller;

import java.io.IOException;
import java.util.List;

import com.tenco.tboard.model.Board;
import com.tenco.tboard.model.User;
import com.tenco.tboard.repository.BoardRepositoryImpl;
import com.tenco.tboard.repository.interfaces.BoardRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private BoardRepository boardRepository;

	public BoardController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		boardRepository = new BoardRepositoryImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("principal") == null) {
			response.sendRedirect(request.getContextPath() + "/user/signin");
			return;
		}
		switch (action) {
		case "/create":
			showCreateBoardForm(request, response, session);
			break;
		case "/list":
			handleListBoards(request, response, session);
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	/**
	 * 게시글 생성 화면 이동
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException
	 * @throws ServletException
	 */
	private void showCreateBoardForm(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {
		if (session != null) {
			User user = (User) session.getAttribute("principal");
			if (user != null) {
				request.setAttribute("userId", user.getId());
			}
		}
		request.getRequestDispatcher("/WEB-INF/views/board/create.jsp").forward(request, response);
	}

	/**
	 * 페이징 처리 하기
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void handleListBoards(HttpServletRequest request, HttpServletResponse response, HttpSession session)
			throws ServletException, IOException {

		// 게시글 목록 조회 기능
		int page = 1; // 기본 페이지 번호
		int pageSize = 3; // 한 페이지당 보여질 게시글 수

		try {
			String pageStr = request.getParameter("page");
			if (pageStr != null) {
				page = Integer.parseInt(pageStr);
			}
		} catch (Exception e) {
			// 유효하지 않은 번호를 마음대로 보낼 경우
			page = 1;
		}
		int offset = (page - 1) * pageSize; // 시작 위치 계산 (offset 값 계산)

		List<Board> boardList = boardRepository.getAllBoards(pageSize, offset);

		// 전체 게시글 수 조회
		int totalBoards = boardRepository.getTotalBoardCount();

		// 총 페이지 수 계산
		int totalPages = (int) Math.ceil((double) totalBoards / pageSize);

		request.setAttribute("totalPages", totalPages);
		request.setAttribute("boardList", boardList);
		request.setAttribute("currentPage", page);

		// 현재 로그인한 사용자 ID 설정
		if (session != null) {
			User user = (User) session.getAttribute("principal");
			if (user != null) {
				request.setAttribute("userId", user.getId());
			}
		}

		request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("principal") == null) {
			response.sendRedirect(request.getContextPath() + "/user/signin");
			return;
		}
		switch (action) {
		case "/create":
			handleCreateBoard(request, response, session);
			break;
		case "/edit":
			break;
		case "/addComment":
			break;

		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	/**
	 * 게시글 생성 처리
	 * 
	 * @param request
	 * @param response
	 * @param session
	 * @throws IOException 
	 */
	private void handleCreateBoard(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
		// 유효성 검사 생략
		Board board = Board.builder()
				.title(request.getParameter("title"))
				.content(request.getParameter("content"))
				.userId(((User) session.getAttribute("principal")).getId())
				.build();
		boardRepository.addBoard(board);
		response.sendRedirect(request.getContextPath() + "/board/list?page=1");
	}

}
