package com.example.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.ToString;

@Entity
@Table(name="tbl_emps")
@Data
@ToString(exclude={"mgr", "dept"})
public class Emp {
	
	public enum Gender {
		M, F;
	}
	
	
	@TableGenerator(name="idGen", table="id_gen", 
					  pkColumnName="seq_name",
					  valueColumnName="nextval",
					  allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="idGen")
	private Long bno;
	@Id
	private Long empno;
	private String ename;
	@Enumerated(EnumType.STRING)
	private Gender gender;
	private String job;
	
	//id에 걸린다.
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="mgr")
	private Emp mgr;
	
	@Temporal(TemporalType.DATE)
	private Date hiredate;
	private BigDecimal sal;
	private BigDecimal comm;
//	private Integer deptno;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="deptno")
	private Dept dept;
	
}



