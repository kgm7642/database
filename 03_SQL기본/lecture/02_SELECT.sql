-- SELECT
-- 특정 테이블에서 원하는 데이터를 조회 할 수 있다.

select -- 조회해줘
    * -- 모든 컬럼을
from -- buytbl 에서
    buytbl;

select
    userID,
    groupName
from
    buytbl;

select(5*5);

select now();
select concat('bear', '안녕', 'kb') as '인 사';

-- usertbl의 name, mobile1, mobile2 를 조회
select
    name, mobile1, mobile2
from usertbl;

-- mobile1, mobile2은 붙어서 전화번호 라는 컬럼으로 조회되어야함
select
    name,
    concat(usertbl.mobile1, usertbl.mobile2) as '전화번호'
from usertbl;
