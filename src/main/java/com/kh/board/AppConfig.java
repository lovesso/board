package com.kh.board;

import com.kh.board.domain.web.intercepter.LoginCheckInterCeptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration //설정  -> 없으면 이중체크를 하지 않는다
public class AppConfig implements WebMvcConfigurer { //스프링프레임워크에서 만들어놓은 인터페이스( WebMvcConfigurer 는 추상메소드가 없다)

  @Override //인터셉터 걸어주기
  public void addInterceptors(InterceptorRegistry registry) { //인터셉터를 여러게 만들 수 있다.

    //인증 체크
    registry.addInterceptor(new LoginCheckInterCeptor())
        .order(1)               //인터셉터 실행 순서를 지정
        .addPathPatterns("/**") //인터셉터에 포함시키는 url패턴, 루트부터 하위 경로 모두. (인터셉터를 수행할 경로를 지정 "/**" -> 모든 경로를 받아들임.)
        .excludePathPatterns(   //화이트리스트 등록 (인터셉터를 제외시키는 화면-로그인 유지x)
            "/",                //초기화면
            "/members/login",           //로그인 처리
            "/members/logout",          //로그아웃 처리
            "/members/join",     //회원가입
            "/css/**",          //css의 하위경로 모두
            "/img/**",
            "/js/**",
            "/test/**",
            "/api/**"
        );   //인터셉터 제외 url패턴
  }
}
