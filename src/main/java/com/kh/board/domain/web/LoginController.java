package com.kh.board.domain.web;

import com.kh.board.domain.entity.Member;
import com.kh.board.domain.member.svc.MemberSVC;
import com.kh.board.domain.web.form.member.LoginForm;
import com.kh.board.domain.web.form.member.LoginMember;
import com.kh.board.domain.web.form.member.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members") //생략가능???
public class LoginController {

  private final MemberSVC memberSVC;

  //로그인 양식(Get)    //로그인 화면
  @GetMapping("/login")
  public String loginForm() {
    return "/member/loginForm"; // view이동
  }

  //로그인처리(Post)     //로그인 양식 작성후 처리
  @PostMapping("/login")                          //세션 정보
  public String login(LoginForm loginForm, HttpServletRequest request, // /login?redirectUrl = 사용자가 요청한 url
                      @RequestParam(value = "redirectUrl", required = false) String redirectUrl) {
    log.info("loginForm={}", loginForm); //포스트맨으로 확인. 콘솔출력확인

    // 1) 유효성 체크
    // 2) 회원 이메일 유무 체크
    if (memberSVC.hasEmail(loginForm.getEmail())) {
      Optional<Member> optionalMember = memberSVC.findByEmailAndPasswd(loginForm.getEmail(), loginForm.getPasswd());

      // 3) 회원이라면(동일이메일 존재),  회원정보를 세션에 저장하기
      if (optionalMember.isPresent()) {
        HttpSession session = request.getSession(true); //세션 생성

        Member member = optionalMember.get(); //회원정보 세선에 저장

        LoginMember loginMember = new LoginMember(
            member.getMemberId(), member.getEmail(),
            member.getNickname(), member.getGubun());

        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

      }
    } else { // 회원이 아닌경우
      return "member/loginForm";
    }
    return redirectUrl != null ? "redirect:" + redirectUrl : "redirect:/";
  }

  //로그아웃
  @ResponseBody
  @PostMapping("/logout")
  public String logout(HttpServletRequest request) {

    String flag = "NoOK";
    //세션있으면 가져오기( 없으면 생성x)
    HttpSession session = request.getSession(false);

    //세션 제거
    if (session != null) {
      session.invalidate();
      flag = "OK";
    }
    return flag;
  }
}
