package com.kh.board.domain.board.svc;

import com.kh.board.domain.board.dao.BoardDAO;
import com.kh.board.domain.entity.Board;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardSVCImpl implements BoardSVC {

  private BoardDAO boardDAO;

  BoardSVCImpl(BoardDAO boardDAO) {
    this.boardDAO = boardDAO;
  }

  //게시글 작성 SVC
  @Override
  public Long save(Board board) {
    return boardDAO.save(board);
  }

  //게시글 조회 SVC

  @Override
  public Optional<Board> findById(Long boardId) {
    return boardDAO.findById(boardId);
  }

  //게시판 목록
  @Override
  public List<Board> findAll() {
    return boardDAO.findAll();
  }

  //게시글 단건 삭제
  @Override
  public int deleteById(Long boardId) {
    return boardDAO.deleteById(boardId);
  }

  //게시글 다중 삭제
  @Override
  public int deleteByIds(List<Long> boardIds) {
    return boardDAO.deleteByIds(boardIds);
  }

  //게시글 수정
  @Override
  public int updateById(Long boardId, Board board) {
    return boardDAO.updateById(boardId, board);
  }
}

