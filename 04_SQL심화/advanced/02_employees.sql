 -- 현재 재직 중인 직원의 정보를 출력하세요
-- ○ 출력 항목: emp_no, first_name, last_name, title
select
    T.emp_no,
    E.first_name,
    E.last_name,
    T.title
from titles T
join employees E
on T.emp_no = E.emp_no;

-- 현재 재직 중인 직원 정보를 출력하세요
-- ○ 출력항목: 직원의 기본 정보 모두, title, salary
select DISTINCT
    E.*,
    T.title,
    S.salary
from employees E
join titles T
    on E.emp_no = T.emp_no
join salaries S
    on E.emp_no = S.emp_no
where S.to_date = '9999-01-01'
order by E.emp_no;

-- 현재 재직중인 직원의 정보를 출력하세요.
-- ○ 출력항목: emp_no, first_name, last_name, department
-- ○ 정렬: emp_no 오름 차순
select
    E.emp_no,
    E.first_name,
    E.last_name,
    D.dept_name
from employees E
join dept_emp DE
    on E.emp_no = DE.emp_no
join departments D
    on DE.dept_no = D.dept_no
where DE.to_date = '9999-01-01'
order by E.emp_no asc;

-- 부서별 재직중인 직원의 수를 출력하세요.
-- ○ 출력 항목: 부서 번호, 부서명, 인원수
-- ○ 정렬: 부서 번호 오름차순

select
    DE.dept_no,
    D.dept_name,
    count(*)
from dept_emp DE
join departments D
    on DE.dept_no = D.dept_no
join employees E
    on DE.emp_no = E.emp_no
where DE.to_date = '9999-01-01'
GROUP BY DE.dept_no, D.dept_name
order by DE.dept_no;

-- 직원 번호가 10209인 직원의 부서 이동 히스토리를 출력하세요.
-- ○ 출력항목: emp_no, first_name, last_name, dept_name, from_date, to_date
 select
     E.emp_no,
     E.first_name,
     E.last_name,
     D.dept_name,
     DE.from_date,
     DE.to_date
 from employees E
     join dept_emp DE
           on E.emp_no = DE.emp_no
     join departments D
           on DE.dept_no = D.dept_no
 where E.emp_no = '10209';


