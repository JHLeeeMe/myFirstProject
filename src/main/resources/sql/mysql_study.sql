
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

############################################################################################################
############################################################################################################
############################################################################################################

drop table tbl_city;

create table tbl_city(
	id int(11) not null,
	name varchar(255),
	code varchar(255),
	district varchar(255),
	population int(11)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into tbl_city
select * from city;

select * from tbl_city;

############################################################################################################
############################################################################################################
############################################################################################################

drop table tbl_country;

create table tbl_country(
	code 						char(3),
	gnpold 					varchar(255),
	localname 				char(45),
	governmentform 		char(45),
	headofstate				char(60),
	capital					int(11),
	code2						char(3),
	name						char(52),
	continent				char,
	region					char(26),
	surfacearea				varchar(255),
	indepyear				smallint(6),
	population				int(11),
	lifeexpectancy			varchar(255),
	gnp						varchar(255)
);
select * from tbl_country;

insert into tbl_country
select * from country;

select *
  from tbl_country
 where name="Charlotte Amalie";
 