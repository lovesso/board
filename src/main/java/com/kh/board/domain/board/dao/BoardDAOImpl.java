package com.kh.board.domain.board.dao;

import com.kh.board.domain.entity.Board;
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

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
public class BoardDAOImpl implements BoardDAO {

  private final NamedParameterJdbcTemplate template; //db와 연동하는 객체

  BoardDAOImpl(NamedParameterJdbcTemplate template) { //생성자
    this.template = template;
  }

  //--------------------------------------------------------------------
  //게시글 생성
  @Override
  public Long save(Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append("insert into board(board_id,title,content,writer) ");
    sql.append("values(board_board_id_seq.nextval, :title, :content, :writer) ");

    //SQL파라미터 자동매칭
    SqlParameterSource param = new BeanPropertySqlParameterSource(board);
    KeyHolder keyHolder = new GeneratedKeyHolder();

    template.update(sql.toString(), param, keyHolder, new String[]{"board_id"});
    long board_id = keyHolder.getKey().longValue();
    return board_id;
  }

  //------------------------------------------------------------------
  //게시글 조회
  @Override
  public Optional<Board> findById(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select board_id,title,content,writer,cdate,udate ");
    sql.append("  from board ");
    sql.append(" where board_id = :boardId ");

    try {
      Map<String, Long> map = Map.of("boardId", boardId);
      Board board = template.queryForObject(sql.toString(), map, BeanPropertyRowMapper.newInstance(Board.class));
      return Optional.of(board);
    } catch (EmptyResultDataAccessException e) {

      return Optional.empty();
    }
  }
  //-------------------------------------------------------------------

  //목록
  @Override
  public List<Board> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select board_id, title, content, writer, cdate, udate ");
    sql.append("from board ");
    sql.append("order by board_id desc ");

    List<Board> list = template.query(sql.toString(), BeanPropertyRowMapper.newInstance(Board.class));
    return list;
  }

  //단건 삭제
  @Override
  public int deleteById(Long boardId) {
    StringBuffer sql = new StringBuffer();
    sql.append("delete from board ");
    sql.append(" where board_id = :boardId ");

    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("boardId", boardId);

    int deletedRowCnt = template.update(sql.toString(), param);

    return deletedRowCnt;
  }

  //여러건 삭제
  @Override
  public int deleteByIds(List<Long> boardIds) {
    StringBuffer sql = new StringBuffer();
    sql.append("delete from board ");
    sql.append(" where board_id in (:boardIds) ");

    Map<String, List<Long>> map = Map.of("boardIds", boardIds);
    int deletedRowCnt = template.update(sql.toString(), map);
    return deletedRowCnt;
  }

  //수정하기
  @Override
  public int updateById(Long boardId, Board board) {
    StringBuffer sql = new StringBuffer();
    sql.append("update board ");
    sql.append("   set title = :title, ");
    sql.append("       content = :content, ");
    sql.append("       writer = :writer, ");
    sql.append("       udate = default ");
    sql.append(" where board_id = :boardId ");


    SqlParameterSource param = new MapSqlParameterSource()
        .addValue("title", board.getTitle())
        .addValue("content", board.getContent())
        .addValue("writer", board.getWriter())
        .addValue("boardId", boardId);


    int updateRowCnt = template.update(sql.toString(), param);

    return updateRowCnt;
  }
}