use tabledb;

/*
VIEW
실제 데이터를 저장하지 않고, 하나 이상의 테이블을 조회하는 select문을
가상의 테이블 처럼 사용한는 객체
- 자주 사용하는 select 쿼리를 재사용 할 수 있게 해줌
- 보안, 편의성, 유지보수성을 높여준다.
- 복잡한 쿼리의 view 테이블을 사용할경우 성능 하락
- 참조하던 테이블이 삭제되면 뷰가 깨질수있어, 뷰를 먼저삭제하는것을 권장함.

create view [뷰이름] as
 [slelct문]
*/
create view v_userbuytbl as
select
    u.userID, u.name, b.prodName, u.addr, concat(u.mobile1, u.mobile2)
from
    usertbl u join buytbl b
on u.userID = b.userID;

select * from v_userbuytbl
where name = '김범수';