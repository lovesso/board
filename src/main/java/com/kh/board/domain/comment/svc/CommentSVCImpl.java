package com.kh.board.domain.comment.svc;

import com.kh.board.domain.comment.dao.CommentDAO;
import com.kh.board.domain.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CommentSVCImpl implements CommentSVC{
  //등록, 조회, 수정, 삭제, 목록
  private CommentDAO commentDAO;

  public CommentSVCImpl(CommentDAO commentDAO) {
    this.commentDAO = commentDAO;
  }


  //댓글 등록
  @Override
  public Long save(Comment comment){
    return commentDAO.save(comment);
  }

  //댓글 목록
  @Override
  public List<Comment> findAll(Long boardId){
    return commentDAO.findAll(boardId);
  }

  //총레코드건수
  @Override
  public int totalCnt() {
    return commentDAO.totalCnt();
  }

  //댓글 삭제
  @Override
  public int deleteById(Long commentsId){
    return commentDAO.deleteById(commentsId);
  }

  //댓글 수정
  @Override
  public int updateById(Long commentsId, Comment comment) {
    return commentDAO.updateById(commentsId, comment);
  }
}
