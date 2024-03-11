package com.kh.board.domain.web.api;
// 공통 응답 메세지 클래스
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiResponse<T> {
  private Header header;
  private T body;
  private int totalCnt = 1;     //총레코드수
  private int recCnt = 1;       //1회 가져올 조회레코드 수
  private int reqPage = 1;      //요청 페이지

  private ApiResponse(Header header, T body) {
    this.header = header;
    this.body = body;
  }


  @Getter
  @ToString
  @AllArgsConstructor
  private static class Header {
    String rtcd;   //rtcd :응답코드
    String rtmsg;   //rtmsg : 응답메세지
    String rtdetail; //응답세부메세지

    Header(String rtcd, String rtmsg) {
      this.rtcd = rtcd;
      this.rtmsg = rtmsg;
    } //아래 정적 메소드를 통해서 생성
  }

  public static <T> ApiResponse<T> createApiResponse(String rtcd, String rtmsg, T body) {
    return new ApiResponse<T>(new Header(rtcd, rtmsg), body);
  }

  public static <T> ApiResponse<T> createApiResponseDetail(String rtcd, String rtmsg, String rtdetail, T body) {
    return new ApiResponse<T>(new Header(rtcd, rtmsg, rtdetail), body);
  }

  public void setTotalCnt(int totalCnt) {
    this.totalCnt = totalCnt;
  }

  public void setRecCnt(int recCnt) {
    this.recCnt = recCnt;
  }

  public void setReqPage(int reqPage) {
    this.reqPage = reqPage;
  }
}

