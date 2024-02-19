-------- 삭제
drop table bBoard;
-- 테이블 삭제
drop table bBoard;
-- 시퀀스 삭제
drop sequence bBoard_bBoard_id_seq;

---------
-- 게시판
--------
create table bBoard(
    bBoard_id      number(10),            -- 게시글 아이디
    title          varchar(90),           -- 제목
    content        varchar(100000)        -- 내용
    writer         varchar(15),           -- 작성자
    cdate          timestamp,             -- 작성날짜
    udate          timestamp              -- 수정날짜
);
--기본키
alter table bBoard add constraint bBoard_bBoard_id_pk primary key(bBoard_id);

--시퀀스생성
create sequence bBoard_bBoard_id_seq;

--디폴트
alter table blog modify cdate default systimestamp; -- 운영체제 일시를 기본값으로
alter table blog modify udate default systimestamp; -- 운영체제 일시를 기본값으로

--생성--
insert into bBoard(bBoard_id, title, content, writer)
     values(bBoard_bBoard_id_seq.nextval, '오늘의 일기', '냉면이 먹고싶은데 겨울이라 파는 곳이 없다. 붕어빵이나 먹자', '가나다');

commit;



