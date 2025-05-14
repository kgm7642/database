select sum(Population) as "인구수 총합"
from city
where CountryCode = 'KOR';

select min(Population) as "최소값"
from city
where CountryCode = 'KOR';

select avg(Population) as "평균"
from city
where CountryCode = 'KOR'

select max(Population) as "최대값"
from city
where CountryCode = 'KOR';

select name, CHAR_LENGTH(name) as 글자수
from country;

select name, UPPER(LEFT(name, 3)) as "앞 세글자"
from country;

select round(LifeExpectancy, 1) as "기대수명"
from country;