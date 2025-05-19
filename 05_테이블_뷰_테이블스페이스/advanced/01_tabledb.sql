-- ------------------- 2page -----------------------------
DROP database if exists tabledb;

create database tabledb;

use tabledb;

drop table if exists usertbl;

create table usertbl
(
    userID   char(8)     not null primary key,
    name     varchar(10) not null,
    birtyear int         not null
);

drop table if exists buytbl;
create table buytbl
(
    num      int auto_increment not null primary key,
    userID   char(8)            not null,
    prodName char(6)            not null
);

ALTER TABLE buytbl
    ADD CONSTRAINT FK_buytbl_usertbl_userID
        FOREIGN KEY (userID)
            REFERENCES usertbl (userID);

-- ------------------- 3page ----------------------------
drop table if exists buytbl;
drop table if exists usertbl;

create table usertbl
(
    userID   char(8)     not null primary key,
    name     varchar(10) not null,
    birtyear int         not null,
    email    char(30) unique
);

-- ------------------- 4page ----------------------------
drop table if exists usertbl;

create table usertbl
(
    userID   char(8) not null primary key,
    name     varchar(10),
    birtyear int CHECK (birtyear >= 1900 AND birtyear <= 2023),
    mobile   char(3) not null
);

-- ------------------- 5page ----------------------------
drop table if exists usertbl;

create table usertbl
(
    -- 컬럼 타입 제약조건
    userID   char(8)     not null primary key,
    name     varchar(10) not null,
    birtyear int         not null default -1,
    addr     char(2)     not null default '서울',
    mobile1  char(3),
    mobile2  char(8),
    height   smallint             default 170,
    mDate    date
);

insert into usertbl (userID, name, mobile1, mobile2, mDate)
values ('LSG', '이승기', '011', '1111111', '2008-08-08');

select * from usertbl;

-- ------------------- 6page ----------------------------
alter table usertbl
drop column mobile1;

alter table usertbl
change column name uName VARCHAR(10) NOT NULL;

alter table usertbl
drop primary key;