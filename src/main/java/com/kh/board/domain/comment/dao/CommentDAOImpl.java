package com.kh.board.domain.comment.dao;

import com.kh.board.domain.entity.Comment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CommentDAOImpl implements CommentDAO {

  private final NamedParameterJdbcTemplate template;

  //등록
  @Override
  public Long save(Comment comment) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into comments(comments_id, board_id, c_content, writer) ");
    sql.append("values(comments_comments_id_seq.nextval, :boardId, :cContent, :writer) ");

    SqlParameterSource param = new BeanPropertySqlParameterSource(comment);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    template.update(sql.toString(), param, keyHolder, new String[]{"comments_id"});
    Long comments_id = ((BigDecimal) keyHolder.getKeys().get("comments_id")).longValue(); //댓글아이디
    return comments_id;
  }

  //댓글 목록_backup
//  @Override
//  public List<Comment> findAll() {
//    StringBuffer sql = new StringBuffer();
//    sql.append("  select comments_id,board_id,c_content,writer,cdate,udate ");
//    sql.append("    from comments ");
//    sql.append("order by comments_id desc ");
//
//    try {
//
//      List<Comment> list = template.query(sql.toString(),
//          BeanPropertyRowMapper.newInstance(Comment.class));
//      return list;
//    } catch (EmptyResultDataAccessException e) {
//      // 조회 결과가 없는 경우
//      return List.of();
//    }
//  }
  // 댓글목록 JOIN
  // 댓글 목록
  @Override
  public List<Comment> findAll(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select c.comments_id, c.board_id, c.c_content, c.writer, c.cdate, c.udate ");
    sql.append("from comments c join board b on c.board_id = b.board_id ");
    sql.append("where c.board_id = :boardId ");
    sql.append("order by comments_id desc ");

    try {
      // (:blogId) 파라미터와 값을 매핑하는 Map 생성
      Map<String, Long> map = Map.of("boardId", boardId);
      List<Comment> list = template.query(sql.toString(), map,
          BeanPropertyRowMapper.newInstance(Comment.class));
      return list;
    } catch (EmptyResultDataAccessException e) {
      // 조회 결과가 없는 경우
      return List.of();
    }

  }

  //총레코드 건수
  @Override
  public int totalCnt() {
    String sql = "SELECT COUNT(comments_id) FROM comments ";

    SqlParameterSource param = new MapSqlParameterSource();
    Integer cnt = template.queryForObject(sql, param, Integer.class);

    return cnt;
  }

  //댓글 삭제
  @Override  //postman에서 500번대 에러발생하지만 데이터베이스에 댓글은 삭제가된다 => controller에서 responsbody하지 않아서
  public int deleteById(Long commentsId) {
    StringBuffer sql = new StringBuffer();
    sql.append("delete from comments");
    sql.append(" where comments_id = :commentsId ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("commentsId", commentsId);

    int deletedRowCnt = template.update(sql.toString(), param);

    return deletedRowCnt;
  }

  // 댓글 수정
  @Override
  public int updateById(Long commentsId, Comment comment) {
    StringBuffer sql = new StringBuffer();
    sql.append("update comments ");
    sql.append("   set c_content = :cContent, ");
    sql.append("           udate = default ");
    sql.append(" where comments_id = :commentsId ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("commentsId", commentsId)
        .addValue("cContent", comment.getCContent());

    //update수행 후 변경된 행수 반환
    int updateRowCnt = template.update(sql.toString(), param);

    return updateRowCnt;

  }
}
