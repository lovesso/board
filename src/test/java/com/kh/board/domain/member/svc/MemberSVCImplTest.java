package com.kh.board.domain.member.svc;

import com.kh.board.domain.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class MemberSVCImplTest {

  @Autowired
  MemberSVC memberSVC;

  @Test
  void joinMember() {
    Member member = new Member();
    member.setEmail("user4@kh.com");
    member.setPasswd(("user4"));
    member.setNickname("사용자4");
    Long memberId = memberSVC.joinMember(member);
    log.info("memberId = {}", memberId); //DB에 사용자4 데이터 잘 들어감!

  }
}

