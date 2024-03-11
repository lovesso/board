package com.kh.board.domain.member.dao;

import com.kh.board.domain.entity.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor //final 생성자
public class MemberDAOImpl implements MemberDAO {

  private final NamedParameterJdbcTemplate template;

  //회원가입
  @Override
  public Long joinMember(Member member) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into member (member_id, email, passwd, nickname) "); //sql에서 정보를 가져온다. 하지만 바뀌는부분은 파라미터로 바꾸기
    sql.append("values(member_member_id_seq.nextval,:email,:passwd,:nickname) ");   //맨끝에 "(쌍따옴표)전에 한칸 띄우기. -> 파라미터는 엔터티 변수와 동일하게

    //sql실행
    //1)sql파라미터 매핑
    SqlParameterSource param = new BeanPropertySqlParameterSource(member);

    //2)변경된 레코드 정보를 읽어오는 용도
    KeyHolder keyHolder = new GeneratedKeyHolder();

    //3)sql실행
    template.update(sql.toString(), param, keyHolder, new String[]{"member_id"});

    //4) insert된 레코드에서 회원 번호를 추출
    Long board_id = ((BigDecimal) keyHolder.getKeys().get("member_id")).longValue();       //여기까지 작성하고 DAO Test만들어서 확인해보기

    return board_id;
  }

  //회원 조회 ---> 1)이메일 조회 2)회원 조회

  // 1)이메일 조회  (db에서 이메일확인필요)
  @Override
  public boolean hasEmail(String email) {
    String sql = "select count(email) from member where email = :email "; //(db에서 이메일확인필요)

    Map param = Map.of("email", email);
    Integer cnt = template.queryForObject(sql, param, Integer.class);
    return cnt == 1 ? true : false; //조회되는 행이 하나 있으면 이메일 있고, 없으면 false
  }

  // 2)회원 조회  //동일한 회원이 있는지? DB에서 확인
  @Override
  public Optional<Member> findByEmailAndPasswd(String email, String passwd) {
    StringBuffer sql = new StringBuffer();
    sql.append("select * from member ");
    sql.append(" where email = :email ");
    sql.append("   and passwd = :passwd ");

    Map<String, String> param = Map.of("email", email, "passwd", passwd);

    try{ //레코드를 받을 수 있는 자바객체가 필요 : new BeanPropertyRowMapper<>(Member.class) 멤버객체에 저장됨
      Member member = template.queryForObject(sql.toString(), param, new BeanPropertyRowMapper<>(Member.class));
      return Optional.of(member);
    }catch(EmptyResultDataAccessException e){
      return Optional.empty();
    }

  }


}
