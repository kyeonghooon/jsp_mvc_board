package com.tenco.tboard.repository.interfaces;

import java.util.List;

import com.tenco.tboard.model.Board;

public interface BoardRepository {
	
	void addBoard(Board board);
	void updateBoard(Board board, int principalId);
	void deleteBoard(int id, int principalId);
	Board getBoardById(int id);
	List<Board> getAllBoards(int limit, int offset);
	int getTotalBoardCount();
	
}
