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


/* 댓글 영역 시작 */

create table tbl_reply(
	rno int NOT NULL AUTO_INCREMENT,
    bno int not null default 0,
    replytext varchar(1000) not null,
    replyer varchar(50) not null,
    regdate TIMESTAMP NOT NULL DEFAULT now(),
    updatedate TIMESTAMP NOT NULL DEFAULT now(),
    primary key(rno)
);


alter table tbl_reply add constraint fk_board foreign key (bno) references tbl_board(bno);

select * from tbl_reply;

insert into tbl_reply(bno, replytext, replyer)
values(98295, '10번댓글', 'hanumoka');

insert into tbl_reply(bno, replytext, replyer)
(select bno, replytext, replyer from tbl_reply);

commit;

/* 로그인 영역 */

create table tbl_user(
	uid varchar(50) NOT NULL,
    upw varchar(50) NOT NULL,
    uname varchar(100) NOT NULL,
    upoint int NOT NULL DEFAULT 0,
    primary key(uid)
);


insert into tbl_user(uid, upw, uname) values('user00', 'user00', 'IRON MAN');
insert into tbl_user(uid, upw, uname) values('user01', 'user01', 'CAPTAIN');
insert into tbl_user(uid, upw, uname) values('user02', 'user02', 'HULK');
insert into tbl_user(uid, upw, uname) values('user03', 'user03', 'Thor');
insert into tbl_user(uid, upw, uname) values('user04', 'user04', 'Quick Silver');

commit;

/* 자동 로그인을 위한 데이터 : 세션쿠키 아이디 저장용*/
alter table tbl_user add column
sessionkey varchar(50) not null default 'none';

/* 자동 로그인을 위한 데이터 : 세션쿠키 유효기간*/
alter table tbl_user add column 
sessionlimit timestamp;






