-- ------------------- 9page -----------------------------
use employees;

create view EMPLOYEES_INFO as
select E.emp_no as emp_no, E.birth_date as birth_date, E.first_name as first_name, E.last_name as last_name, E.gender as gender, E.hire_date as hire_date, T.title as title, T.from_date as t_from, T.to_date as t_to, s.salary as salary, s.from_date as s_from, s.to_date as s_to
from employees E
join titles T
on E.emp_no = T.emp_no
join salaries S
on E.emp_no = S.emp_no;

-- ------------------- 10page -----------------------------
select *
from EMPLOYEES_INFO
where s_to = '9999-01-01';

-- ------------------- 11page -----------------------------
create view EMP_DEPT_INFO as
select E.emp_no as emp_no, DE.dept_no as dept_no, D.dept_name as dept_name, DE.from_date as from_date, DE.to_date as to_date
from employees E
         join dept_emp DE
              on E.emp_no = DE.emp_no
         join departments D
              on DE.dept_no = D.dept_no
where to_date = '9999-01-01';

-- ------------------- 12page -----------------------------
select *
from EMP_DEPT_INFO
where to_date = '9999-01-01';