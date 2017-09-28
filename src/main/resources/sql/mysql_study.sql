
select * from dept;
select * from emp;

update emp
   set gender = 'F'
 where gender = 'f';
commit; 

select * from tbl_dept;
select * from tbl_emp;

insert into tbl_dept
select * from dept;

insert into tbl_emp
(comm, deptno, empno, ename, gender, hiredate, job, mgr, sal)
select comm, deptno, empno, ename, gender, hiredate, job, mgr, sal from emp;

insert into tbl_profile
(fname, curr)
values
("user01_profile", 1);

select * from tbl_profile;

############################################################################################################
############################################################################################################
############################################################################################################

select * from tbl_webboards limit 0, 10;
select count(*) from tbl_webboards;
select * from id_gen;


 