package com.example.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;
import lombok.ToString;

@Data
@Entity // 클래스가 엔티티 클래스임을 명시
@Table(name = "tbl_country") // 데이터베이스에 동일한 이름으로 생성
@ToString(exclude = { "citys" })
public class Country {

	@TableGenerator(name="idGen", table="id_gen",
			  pkColumnName="seq_name",
			  valueColumnName="nextval",
			  allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="idGen")
	private Long bno;
	@Id
	private String code;
	private Float gnpold;
	private String localname;
	private String governmentform;
	private String headofstate;
	private Integer capital;
	private String code2;
	private String name;
	private String continent;
	private String region;
	private Float surfacearea;
	private Short indepyear;
	private Integer population;	
	private Float lifeexpectancy;
	private Float gnp;
	
	@OneToMany(mappedBy = "country", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	List<City> citys;
}
