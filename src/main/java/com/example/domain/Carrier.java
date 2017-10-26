package com.example.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

@Data
@Entity // 클래스가 엔티티 클래스임을 명시
@Table(name = "tbl_carrier") // 데이터베이스에 동일한 이름으로 생성
@ToString(exclude = { "ontimes" })
public class Carrier {

   @Id
   private String code;
   private String description;
   
   @OneToMany(mappedBy="carrier", fetch=FetchType.LAZY)
   private List<Ontime> ontimes;
   
}