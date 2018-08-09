-- docker stop $(docker ps -a -q)
-- docker-compose start


create table tbl_member (
	userid varchar(50) not null,
    userpw varchar(50) not null,
    username varchar(50) not null,
    email varchar(100),
    regdate timestamp default now(),
    updatedate timestamp default now(),
    primary key(userid)
);

SHOW DATABASES;

SHOW TABLES;

desc tbl_member;

select * from tbl_member;


create table tbl_board (
	bno INT NOT NULL auto_increment,
    title varchar(200) NOT NULL,
    content TEXT NULL,
    writer VARCHAR(50) NOT NULL,
    regdate timestamp NOT NULL DEFAULT now(),
    viewcnt INT DEFAULT 0,
    primary key (bno)

);

insert into tbl_board(title, content, writer)
values('제목입니다', '내용입니다', 'user00');

select * from tbl_board where bno = 1;

select * from tbl_board where bno > 0 order by bno desc;

update tbl_board set title ='수정된 제목' where bno = 1;

delete from tbl_board where bno = 1;

desc tbl_board;

drop table tbl_board;
commit;

select * from tbl_board;

select
				bno, title, content, writer, regdate, viewcnt
			from
				tbl_board
			where bno > 0
			order by bno desc, regdate desc;
            
            
/* 게시판 페이징 처리 용 쿼리 시작 */

select * from tbl_board 
where bno > 0 
order by bno desc 
limit 0, 10;     

select * from tbl_board 
where bno > 0 
order by bno desc 
limit 10, 10;    

/* 게시판 데이터 불리기  */

insert into tbl_board(title, content, writer)
(select title, content, writer from tbl_board);

select count(*) from tbl_board;
      
select
				bno, title, content, writer, regdate, viewcnt
			from
				tbl_board
			where bno > 0
			order by bno desc, regdate desc
limit 0, 20;    


select * from tbl_board where bno > 0 order by bno asc
limit 0, 10;
;

