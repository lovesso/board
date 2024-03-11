package com.kh.board.domain.web;


import com.kh.board.domain.entity.Member;
import com.kh.board.domain.member.svc.MemberSVC;
import com.kh.board.domain.web.form.member.JoinForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class JoinController {

  private final MemberSVC memberSVC;

  // 회원가입 양식
  @GetMapping("/join")
  public String joinForm() {
    return "member/joinForm";
  }

  // 회원가입 처리 ---> (1) 유효성검증 (2) 저장
  @PostMapping("/join") //from joinForm, view
  public String join(JoinForm joinForm) {
    log.info("joinForm={}", joinForm); //postman으로 확인, 콘솔에 찍힘. 성공!

    //
    //1) 유효성 검증
    //2) 저장 처리
    Member member = new Member();//회원 객체 생성
    BeanUtils.copyProperties(joinForm, member); //joinForm 속성을 -> member 객체에 넣어줌
    Long memberId = memberSVC.joinMember(member); //member객체를 서비스로
    //---> 포스트맨으로 확인. DB에 저장됨!


    return "redirect:/";
  }
}
