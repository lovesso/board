package com.kh.board.domain.web;

import com.kh.board.domain.comment.svc.CommentSVC;
import com.kh.board.domain.entity.Comment;
import com.kh.board.domain.web.api.ApiResponse;
import com.kh.board.domain.web.api.ResCode;
import com.kh.board.domain.web.req.comments.ReqSave;
import com.kh.board.domain.web.req.comments.ReqUpdate;
import com.kh.board.domain.web.req.comments.ResSave;
import com.kh.board.domain.web.req.comments.ResUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RestController
@RequiredArgsConstructor
@Slf4j
@Controller
@RequestMapping("/api/comments")
//@RestController = Controller + ResponsBody
public class ApiCommentsController {

  private final CommentSVC commentSVC;

  //댓글 등록

  @ResponseBody
  @PostMapping   //POST http:?/localhost:9080/api/comments
  public ApiResponse<?> save(@RequestBody ReqSave reqSave){
    log.info("reqSave={}", reqSave);
    //1) 유효성검증

    //2) 댓글 등록 처리
    Comment comment = new Comment();
    BeanUtils.copyProperties(reqSave, comment); //reqSave를 product객체에다가 복사
//    productSVC.save(product); //DAO에 저장됨.
    Long commentId = commentSVC.save(comment);

    ResSave resSave = new ResSave(commentId, reqSave.getBoardId(), reqSave.getCContent(), reqSave.getWriter());

    String rtDetail = "댓글번호 " + commentId + " 가 등록 되었습니다";
    ApiResponse<ResSave> res = ApiResponse.createApiResponseDetail(
        ResCode.OK.getCode(), ResCode.OK.name(), rtDetail, resSave);
    return res;
  }

  //3)댓글 목록
  @ResponseBody
  @GetMapping
  public ApiResponse<?> findAll(@RequestParam("boardId") Long boardId){
    List<Comment> list = commentSVC.findAll(boardId);

    ApiResponse<List<Comment>> res = null;

    if(list.size() > 0) {
      res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), list);
      res.setTotalCnt(commentSVC.totalCnt());
    }else{
      res = ApiResponse.createApiResponseDetail(
              ResCode.OK.getCode(), ResCode.OK.name(), "등록된 댓글이 1건도 없습니다.", list);
    }
    return res;
  }

  // 댓글 삭제
  @ResponseBody
  @DeleteMapping("/{cid}")
  public ApiResponse<?> deleteById(@PathVariable("cid") Long commentsId){
    log.info("cid={}", commentsId);

    int deletedCnt = commentSVC.deleteById(commentsId);
    ApiResponse<ResUpdate> res = null;

    if(deletedCnt == 1){
      res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), null);
    }else{
      res = ApiResponse.createApiResponse(ResCode.FAIL.getCode(), ResCode.FAIL.name(), null);
    }
    return res;
  }

  // 댓글 수정
  @ResponseBody
  @PatchMapping("/{cid}")
  public ApiResponse<?> updateById(@PathVariable("cid") Long commentsId,
                                   @RequestBody ReqUpdate reqUpdate) {
    log.info("cid = {}", commentsId);
    log.info("reqUpdate = {}", reqUpdate);
    //1) 유효성 체크

    //2)수정
    Comment comments = new Comment();
    BeanUtils.copyProperties(reqUpdate, comments);

    int updateCnt = commentSVC.updateById(commentsId, comments);

    ApiResponse<ResUpdate> res = null; // 공통 응답메세지 바디에 수정한 응답메세지 초기화
    if (updateCnt == 1) {  // 업데이트 건수가 있으면
      ResUpdate resUpdate = new ResUpdate(); // 응답 객체
      BeanUtils.copyProperties(reqUpdate, resUpdate); // 요청 객체를 응답 객체에 넣기
      resUpdate.setCommentsId(commentsId); // 응답 body에 댓글ID 넣기
      log.info("resUpdate = {}", resUpdate); // 응답 데이터 확인
      res = ApiResponse.createApiResponse(ResCode.OK.getCode(), ResCode.OK.name(), resUpdate); // 공통 응답메세지
    } else { // 업데이트 건수가 없으면
      String rtDetail = "수정 실패";
      res = ApiResponse.createApiResponseDetail(
          ResCode.FAIL.getCode(), ResCode.FAIL.name(), rtDetail, null);
    }
    return res;
  }
}
