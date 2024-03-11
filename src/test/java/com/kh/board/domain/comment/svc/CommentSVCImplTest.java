package com.kh.board.domain.comment.svc;

import com.kh.board.domain.entity.Comment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class CommentSVCImplTest {
  @Autowired
  CommentSVC commentSVC;

  @DisplayName("댓글등록")
  //@Test
  void save() {
    Comment comment = new Comment();

    comment.setWriter("콩콩이");
    comment.setCContent("댓글이에요.");
    Long commentId = commentSVC.save(comment);
    log.info("comment={}", commentId);
  }
}