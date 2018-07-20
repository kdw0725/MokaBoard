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