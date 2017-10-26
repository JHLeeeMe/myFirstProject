package com.example.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.domain.Emp;
import com.example.domain.QDept;
import com.example.domain.QEmp;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface EmpRepository extends JpaRepository<Emp, Long>, QuerydslPredicateExecutor<Emp> {

	// dynamic쿼리를 만들어내기 위함
	public default Predicate makePredicate(String type, String keyword) {

		BooleanBuilder builder = new BooleanBuilder();

		QEmp emp = QEmp.emp;
		QDept dept = QDept.dept;

		builder.and(emp.empno.gt(0));

		if (type == null) {
			return builder;
		}

		switch (type) {
		case "no":
			builder.and(emp.empno.like("%" + keyword + "%"));
			break;
		case "na":
			builder.and(emp.ename.like("%" + keyword + "%"));
			break;
//		case "g":
//			builder.and(emp.gender.eq((EnumPath<Emp.Gender>)("%" + keyword + "%"));
//			break;
		case "j":
			builder.and(emp.job.like("%" + keyword + "%"));
			break;
		case "dno":
			builder.and(dept.deptno.like("%" + keyword + "%"));
			break;
		}

		return builder;
	}

}
