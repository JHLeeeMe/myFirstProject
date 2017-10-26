
select * from dept;
select * from emp;

update emp
   set gender = 'F'
 where gender = 'f';
commit;

insert into tbl_depts
select * from dept; 

select * from tbl_depts;
select * from tbl_emps;

insert into tbl_depts
select * from dept;

insert into tbl_emps
(empno, comm, ename, gender, hiredate, job, sal, deptno, mgr)
select empno, comm, ename, gender, hiredate, job, sal, deptno, mgr from emp;

insert into tbl_emps
values (1000, null, null, null, null, null, null, null, null);


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


 