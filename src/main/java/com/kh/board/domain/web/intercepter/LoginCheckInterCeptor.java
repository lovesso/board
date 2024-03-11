package com.kh.board.domain.web.intercepter;

import com.kh.board.domain.web.form.member.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class LoginCheckInterCeptor implements HandlerInterceptor {
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

    String requestURI = request.getRequestURI();
    log.info("요청url = {}", requestURI); ///products/news , uri는 경로만 보여줌
    log.info("요청url={}",request.getRequestURL()); // http://localhost:9080/products/news

    HttpSession session = request.getSession();
    if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
      response.sendRedirect("/members/login");
    }
    return true;
  }
}
