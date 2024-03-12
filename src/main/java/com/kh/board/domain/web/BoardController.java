package com.kh.board.domain.web;

import com.kh.board.domain.board.svc.BoardSVC;
import com.kh.board.domain.entity.Board;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/board")  // http://localhost:9080/board
public class BoardController {

  private BoardSVC boardSVC;

  public BoardController(BoardSVC boardSVC) {
    this.boardSVC = boardSVC;
  }


  //게시글 등록 양식
  @GetMapping("/add")
  public String addForm() {
    return "board/add";
  }

  //게시글 등록
  @PostMapping("/add")
  public String add(
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam("writer") String writer,
      Model model,
      RedirectAttributes redirectAttributes
  ) {

    log.info("writer={}, {}, {}", title, content, writer);

    //게시글 등록
    Board board = new Board();
    board.setWriter(writer);
    board.setTitle(title);
    board.setContent(content);

    Long boardId = boardSVC.save(board);
    log.info("게시글번호={}", boardId);

    redirectAttributes.addAttribute("bid", boardId);
    return "redirect:/board/{bid}/detail";
  }

  //게시글 조회
  @GetMapping("{bid}/detail")
  public String findById(@PathVariable("bid") Long boardId, Model model) {

    Optional<Board> foundBoard = boardSVC.findById(boardId);
    Board board = foundBoard.orElseThrow();
    model.addAttribute("board", board);

    return "board/detailForm"; //view 이동
  }

  // 게시판 목록
  @GetMapping// http://localhost:9080/board?reqPage=2&reqCnt=5
  public String findAll(Model model,
                        @RequestParam(value = "reqPage", defaultValue = "1") Long reqPage,
                        @RequestParam(value = "recCnt", defaultValue = "5") Long recCnt,
                        @RequestParam(value = "cpgs", defaultValue = "1") Long cpgs,
                        @RequestParam(value = "cp", defaultValue = "1") Long cp) {

    List<Board> list = boardSVC.findAll(reqPage, recCnt);
    int totalCnt = boardSVC.totalCnt();
    model.addAttribute("list", list);
    model.addAttribute("totalCnt", totalCnt);
    model.addAttribute("cpgs", cpgs);
    model.addAttribute("cp", cp);

    return "board/allByPaging";
  }

  //단건 삭제
  @GetMapping("/{bid}/del")
  public String deleteById(@PathVariable("bid") Long boardId) {
    log.info("deleteById={}", boardId);

    //1) 게시글 세부사항에서 단건 삭제
    boardSVC.deleteById(boardId);

    return "redirect:/board";
  }

  //다건 삭제
  @PostMapping("/del")
  public String deleteByIds(@RequestParam("bids") List<Long> bids) {
    log.info("deleteByIds={}", bids);
    int deletedRowCnt = boardSVC.deleteByIds(bids);

    return "redirect:/board";
  }

  //수정양식
  @GetMapping("/{bid}/edit")
  public String updateForm(
      @PathVariable("bid") Long boardId, Model model) {

    log.info("updateForm called for boardId: {}", boardId);


    Optional<Board> optionalBoard = boardSVC.findById(boardId);
    Board foundBoard = optionalBoard.orElseThrow();

    model.addAttribute("board", foundBoard);
    return "board/updateForm";


  }
  //수정 처리
  @PostMapping("/{bid}/edit")
  public String update(
      @PathVariable("bid") Long boardId,
      @RequestParam("title") String title,
      @RequestParam("content") String content,
      @RequestParam("writer") String writer,

      RedirectAttributes redirectAttributes) {
    Board board = new Board();
    board.setTitle(title);
    board.setContent(content);
    board.setWriter(writer);

    boardSVC.updateById(boardId, board);

    redirectAttributes.addAttribute("bid", boardId);
    return "redirect:/board/{bid}/detail";
  }
}