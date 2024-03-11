package com.kh.board.domain.member.svc;

import com.kh.board.domain.entity.Member;
import com.kh.board.domain.member.dao.MemberDAO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor// final 생성자
public class MemberSVCImpl implements MemberSVC {

  private final MemberDAO memberDAO;

  //회원가입
  @Override
  public Long joinMember(Member member) {
    return memberDAO.joinMember(member);
  }

  //회원 유무체크
  @Override
  public boolean hasEmail(String email) {
    return memberDAO.hasEmail(email);
  }

  @Override
  public Optional<Member> findByEmailAndPasswd(String email, String passwd) {
    return memberDAO.findByEmailAndPasswd(email, passwd);
  }
}
