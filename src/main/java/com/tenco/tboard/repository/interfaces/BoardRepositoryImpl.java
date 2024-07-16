package com.tenco.tboard.repository.interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.tenco.tboard.model.Board;
import com.tenco.tboard.util.DBUtil;

public class BoardRepositoryImpl implements BoardRepository {

	private final String SELECT_ALL_BOARDS = " SELECT * FROM board ORDER BY created_at DESC limit ? offset ? ";
	private final String COUNT_ALL_BOARDS = " SELECT count(*) AS count FROM board ";

	@Override
	public void addBoard(Board board) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoard(Board board, int principalId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(int id, int principalId) {
		// TODO Auto-generated method stub

	}

	@Override
	public Board getBoardById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Board> getAllBoards(int limit, int offset) {
		List<Board> boardList = new ArrayList<>();

		try (Connection conn = DBUtil.getConnection();//
				PreparedStatement pstmt = conn.prepareStatement(SELECT_ALL_BOARDS)) {
			pstmt.setInt(1, limit);
			pstmt.setInt(2, offset);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				boardList.add(Board.builder()
					.id(rs.getInt("id"))
					.userId(rs.getInt("user_id"))
					.title(rs.getString("title"))
					.content(rs.getString("content"))
					.createdAt(rs.getTimestamp("created_at"))
					.build());
			}
			System.out.println("BoardRepositoryImpl - 로깅 : count " + boardList.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return boardList;
	}

	@Override
	public int getTotalBoardCount() {
		int totalBoards = 0;

		try (Connection conn = DBUtil.getConnection();//
				PreparedStatement pstmt = conn.prepareStatement(COUNT_ALL_BOARDS)) {
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				totalBoards = rs.getInt("count");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return totalBoards;
	}

}
