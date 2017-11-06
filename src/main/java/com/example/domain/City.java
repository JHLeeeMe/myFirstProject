package com.example.domain;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.ToString;

@Data
@Entity // 클래스가 엔티티 클래스임을 명시
@Table(name = "tbl_city") // 데이터베이스에 동일한 이름으로 생성
@ToString(exclude = { "country" }) // ToString 메소드 생성, exclude = { "country" } ==> country는 제외
public class City {

	@TableGenerator(name="idGen", table="id_gen",
			  pkColumnName="seq_name",
			  valueColumnName="nextval",
			  allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="idGen")
	private Long bno;
	@Id
	private Integer id;
	private String name;
	private String district;
	private Integer population;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name = "code")
	private Country country;

	
}
