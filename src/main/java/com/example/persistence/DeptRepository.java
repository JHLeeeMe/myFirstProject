package com.example.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.domain.Dept;
import com.example.domain.QDept;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface DeptRepository extends JpaRepository<Dept, Long>, QuerydslPredicateExecutor<Dept> {

	// dynamic쿼리를 만들어내기 위함
	public default Predicate makePredicate(String type, String keyword) {

		BooleanBuilder builder = new BooleanBuilder();

		QDept dept = QDept.dept;

		builder.and(dept.deptno.gt(0));

		if (type == null) {
			return builder;
		}

		switch (type) {
		case "no":
			builder.and(dept.deptno.like("%" + keyword + "%"));
			break;
		case "na":
			builder.and(dept.dname.like("%" + keyword + "%"));
			break;
		case "l":
			builder.and(dept.loc.like("%" + keyword + "%"));
			break;
		}

		return builder;
	}

}
