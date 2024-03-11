package com.kh.board.domain.comment.dao;

import com.kh.board.domain.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class CommentDAOImplTest {

  @Autowired
  CommentDAO commentDAO;


  @Test
  @DisplayName("댓글 등록")
  void save() {
    Comment comment = new Comment();
    comment.setBoardId(1L);
    comment.setCContent("TEST");
    comment.setWriter("테스터");
    Long commentId = commentDAO.save(comment);
    log.info("commentId = {}", commentId);
  }
  @Test
  @DisplayName("해당 게시글의 댓글 목록")
  void findAll() {
    List<Comment> list = commentDAO.findAll(1L);
    for(Comment comment : list) {
      log.info("comment = {}", comment);
    }
    log.info("size={}", list.size());
  }

  @Test
  @DisplayName("댓글삭제")
  void deleteById() {
    Long cid = 65L;
    int deletedRowCnt = commentDAO.deleteById(cid);
    Assertions.assertThat(deletedRowCnt).isEqualTo(1);
  }

  @Test
  @DisplayName("댓글수정")
  void updateById() {
    Comment c = new Comment();
    c.setCContent("푸바오 떠나지마");
    int updatedRowCnt = commentDAO.updateById(85L, c);
    log.info("updatedRowCnt={}", updatedRowCnt);
    if(updatedRowCnt == 0) {
      Assertions.fail("수정 내역 없음");
    }
  }
}