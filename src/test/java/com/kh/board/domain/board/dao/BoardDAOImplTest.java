package com.kh.board.domain.board.dao;

import com.kh.board.domain.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@SpringBootTest //스프링부트 테스트환경 실행
class BoardDAOImplTest {

  @Autowired //스프링부트 컨테이너 객체를 주입
  BoardDAO boardDAO;

  @Test
  @DisplayName("게시글작성")
  void save() {
    Board board = new Board(); //Board객체
    board.setTitle("게시글제목");
    board.setContent("현재 포트폴리오 작성중");
    board.setWriter("작성자1");

    Long boardId = boardDAO.save(board);
    log.info("boardId={}", boardId);
  }

  @Test
  @DisplayName("게시글 조회")
  void findById() {
    Long boardId = 1L;
    Optional<Board> foundBoard = boardDAO.findById(boardId);
    Board board = foundBoard.orElse(null);
    log.info("board = {}", board);
  }

  @Test
  @DisplayName("게시판 목록")
  void findAll() {
    List<Board> list = boardDAO.findAll(1L, 5L);
    for (Board board : list) {
      log.info("board={}", board);
    }
    log.info("size={}", list.size());
  }

  @Test
  @DisplayName("게시글 단건 삭제")
  void deleteById() {
    Long bid = 23L; //삭제할 게시글 번호
    int deletedRowCnt = boardDAO.deleteById(bid);
    Assertions.assertThat(deletedRowCnt).isEqualTo(1);
  }

  @Test
  @DisplayName("게시글 다건 삭제")
  void deleteByIds() {
    List<Long> ids = new ArrayList<>();
    ids.add(1L);
    ids.add(2L);
    int deletedRowCnt = boardDAO.deleteByIds(ids);
    Assertions.assertThat(deletedRowCnt).isEqualTo(ids.size());
  }

  @Test
  void updateById() {
    Long boardId = 24L;
    Board board = new Board();
    board.setTitle("ssss");
    board.setContent("sssssssssss");
    board.setWriter("ssss");
    int updatedRowCnt = boardDAO.updateById(boardId, board);

    if(updatedRowCnt == 0){
      Assertions.fail("변경 내역이 없습니다.");
    }
    Optional<Board> optionalBoard = boardDAO.findById(boardId);
    if(optionalBoard.isPresent()){
      Board foundBoard = optionalBoard.get();
      Assertions.assertThat(foundBoard.getTitle()).isEqualTo("ssss");
      Assertions.assertThat(foundBoard.getContent()).isEqualTo("sssssssssss");
      Assertions.assertThat(foundBoard.getWriter()).isEqualTo("ssss");

    }
  }

//  @Test
//  @DisplayName("게시글 페이징 전체 조회")
//  void findAllMultiple(){
//    List<Board> list = boardDAO.findAll(1L, 10L);
//    for (Board board : list){
//      log.info("board={}", board);
//    }
//    log.info("size={}", list.size());
//  }
}