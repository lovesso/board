-------- 삭제
-- 테이블 삭제
drop table comments;
drop table board;
-- 시퀀스 삭제
drop sequence board_board_id_seq;
drop sequence comments_comments_id_seq;

---------
--게시판 테이블
---------
create table board(
    board_id      number(10),                              -- 게시글 아이디
    title          varchar2(100),                          -- 제목
    content        clob,                                   -- 내용
    writer         varchar2(50),                           -- 작성자
    cdate          timestamp,                              -- 작성날짜
    udate          timestamp                               -- 수정날짜
);


---------
--댓글 테이블
---------
create table comments(
  comments_id     number(10),   -- 댓글id
  board_id         number(10),  -- 게시글id
  c_content        clob,        -- 내용
  writer          varchar2(50), -- 작성자
  cdate           timestamp,    -- 작성날짜
  udate           timestamp     -- 수정날짜
);


--기본키
alter table board add constraint board_board_id_pk primary key(board_id);

alter table comments add constraint comments_comments_id primary key(comments_id);

--외래키
alter table comments add constraint comments_board_id_fk
                      foreign key (board_id) references board;

--시퀀스생성
create sequence board_board_id_seq;
create sequence comments_comments_id_seq;

--디폴트
alter table board modify cdate default systimestamp; -- 운영체제 일시를 기본값으로
alter table board modify udate default systimestamp; -- 운영체제 일시를 기본값으로

alter table comments modify cdate default systimestamp; -- 운영체제 일시를 기본값으로
alter table comments modify udate default systimestamp; -- 운영체제 일시를 기본값으로

--필수값들 NOT NULL
alter table board modify title not null;
alter table board modify writer not null;
alter table board modify content not null;
alter table comments modify board_id not null;
alter table comments modify writer not null;
alter table comments modify c_content not null;


-- 게시판 데이터
insert into board(board_id, title, content, writer)
     values(board_board_id_seq.nextval, '맛있는 음식', '겨울엔 역시 붕어빵', '가나다');

insert into board(board_id, title, content, writer)
     values(board_board_id_seq.nextval, '등업신청', '등업부탁드립니다.', '마바사');

insert into board(board_id, title, content, writer)
     values(board_board_id_seq.nextval, '디즈니랜드', '가보신적 있으신분 있나요?', '타파하');


-- 댓글 데이터
insert into comments(comments_id, board_id, c_content, writer)
     values(comments_comments_id_seq.nextval, '1', '안녕하세요 반갑습니다', '안녕');
insert into comments(comments_id, board_id, c_content, writer)
     values(comments_comments_id_seq.nextval, '1', '저도 동의합니다.', '완전동의');
insert into comments(comments_id, board_id, c_content, writer)
     values(comments_comments_id_seq.nextval, '2', '푸바오떠나지마~', '푸바오사랑');
insert into comments(comments_id, board_id, c_content, writer)
     values(comments_comments_id_seq.nextval, '2', '언제 만날래', '안녕');
insert into comments(comments_id, board_id, c_content, writer)
     values(comments_comments_id_seq.nextval, '1', '안녕하세요', '방가');
insert into comments(comments_id, board_id, c_content, writer)
     values(comments_comments_id_seq.nextval, '3', '너같은 놈은 무플이 답이야', '어이없어');

commit;

--댓글 쿼리문 준비

select * from comments;
select * from board;

---------------------------------------------------
--멤버 테이블 생성

-- 테이블 삭제
drop table member;
-- 시퀀스 삭제
drop sequence member_member_id_seq;

-- 회원테이블 생성
create table member(
    member_id     number(10),    -- 내부관리 아이디
    email         varchar2(50),  -- 로그인 아이디
    passwd        varchar2(12),  -- 로그인 비밀번호
    nickname      varchar2(30),  -- 별칭
    gubun         varchar(11),   -- default 'M0101', -- 회원구분(일반, 우수, 관리자1, 관리자2...)
    pic           blob,          -- 사진
    cdate         timestamp,     -- 생성일시, 가입일
    udate         timestamp      -- 수정일시
);

-- 기본키 생성
alter table member add constraint member_member_id_pk primary key(member_id);

--시퀀스생성
create sequence member_member_id_seq;

--디폴트
alter table member modify cdate default systimestamp; -- 운영체제 일시를 기본값으로
alter table member modify udate default systimestamp; -- 운영체제 일시를 기본값으로
alter table member modify gubun default 'M0101'; -- 회원구분

-- 유니크
alter table member modify email constraint member_email_uk unique;

--필수값들 NOT NULL
alter table member modify email not null;
alter table member modify passwd not null;
alter table member modify nickname not null;


--샘플데이터 생성
insert into member(member_id,email,passwd,nickname)
     values(member_member_id_seq.nextval, 'user1@naver.com', 'user1', '사용자1');
insert into member(member_id,email,passwd,nickname)
     values(member_member_id_seq.nextval, 'user2@naver.com', 'user2', '사용자2');
insert into member(member_id,email,passwd,nickname)
     values(member_member_id_seq.nextval, 'user3@naver.com', 'user3', '사용자3');

commit;

-- 쿼리문 준비
select * from member;