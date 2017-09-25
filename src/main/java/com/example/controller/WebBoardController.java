package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.vo.PageVO;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/board")
@Log
public class WebBoardController{

	@GetMapping("/list")
	public String list(@ModelAttribute("pageVO") PageVO vo, Model model) {
		log.info("/board/list list()....");
		log.info("PageVO = " + vo);
		return "jsp/board/list";
	}
	
}
