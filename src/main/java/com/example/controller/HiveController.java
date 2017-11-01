package com.example.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vo.PageVO;
import com.mysql.jdbc.PreparedStatement;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/hive")
@Log
public class HiveController {

	@GetMapping("/yeardelay")
	public String yeardelay(PageVO vo,Model model) throws ClassNotFoundException, SQLException {
		log.info("/hive/yeardelay...");
		
		/*
		 * Hive 작업 수행
		 */
		Class.forName("org.apache.hive.jdbc.HiveDriver");
		Connection conn = DriverManager.getConnection("jdbc:hive2://localhost:10000","","");
		PreparedStatement pstmt = (PreparedStatement) conn.createStatement();
		System.out.println(conn);
		
		ResultSet rs = pstmt.executeQuery("select * from year_delay");
		rs = pstmt.executeQuery("select * from ontime limit 20");
		
		while(rs.next()) {
//			System.out.println(rs.getInt(1) + ", " + rs.getInt(2) + ", " + rs.getInt(3) + ", " + rs.getFloat(4));
			System.out.println(rs.getInt(1) + ", " + rs.getInt(2));
		}
		
		rs.close();
		pstmt.close();
		conn.close();
		
		return "jsp/hive/yeardelay";
	}
	
}
