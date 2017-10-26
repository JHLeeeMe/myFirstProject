package com.example.domain;

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
@Table(name = "tbl_ontime") // 데이터베이스에 동일한 이름으로 생성
@ToString(exclude = { "carrier", "airport"}) 
public class Ontime {

	@Id
	@TableGenerator(name="idGen", table="id_gen", 
	  				  pkColumnName="seq_name",
	  				  valueColumnName="nextval",
	  				  allocationSize=1, initialValue=100)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="idGen")
	private Long no;
	
	private Integer year;
	private Integer fligthtNum;
	private String taiNum;
	private Integer actualElapsedTime;
	private Integer crsElapsedTime;
	private Integer airTime;
	private Integer arrDelay;
	private Integer depDelay;
	private String origin;
	private String dest;
	private Integer distance;
	private Integer month;
	private Integer taxiIn;
	private Integer taxiOut;
	private Integer cancelled;
	private String cancellationCode;
	private String diverted;
	private Integer carrierDelay;
	private Integer weatherDelay;
	private Integer nasDelay;
	private Integer securityDelay;
	private Integer lateAircraftDelay;
	private Integer dayOfMonth;
	private Integer dayOfWeek;
	private Integer depTime;
	private Integer crsDepTime;
	private Integer arrTime;
	private Integer crsArrTime;
	private String uniqueCarrier;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "code")
	private Carrier carrier;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="iata")
	private Airport airport;

}