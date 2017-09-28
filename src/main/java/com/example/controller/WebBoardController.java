package com.example.controller;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.WebBoard;
import com.example.persistence.WebBoardRepository;
import com.example.vo.PageMaker;
import com.example.vo.PageVO;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/board")
@Log
public class WebBoardController{

	@Inject
	WebBoardRepository repo;
	
	@GetMapping("/list")
	public String list(@ModelAttribute("pageVO") PageVO vo, Model model) {

		//db갔다 오는 logic
		Pageable pageable = vo.makePageable(0, "bno");
		Predicate predicate = repo.makePredicate(vo.getType(), vo.getKeyword());

		Page<WebBoard> result = repo.findAll(predicate, pageable);

		log.info("" + pageable);
		log.info("" + result);

		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());

		model.addAttribute("pageMaker", new PageMaker<WebBoard>(result));

		return "jsp/board/list";

	}
	
	@GetMapping("/register")
	public String registerGET(@ModelAttribute("vo") WebBoard vo) {
		log.info("register get");
		vo.setTitle("샘플 게시물 제목입니다....");
		vo.setContent("내용을 처리해 봅니다 ");
		vo.setWriter("user00");

		return "jsp/board/register";
	}

	//post는 전문의 body로 들어옴, query형태 x
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") WebBoard vo, RedirectAttributes rttr) {

		log.info("register post");
		log.info("" + vo);

		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/board/list";
	}
	
	@GetMapping("/view")
	public String view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("BNO: " + bno);

		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));

		return "jsp/board/view";

	}
	
	@GetMapping("/modify")
	public String modify(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("MODIFY BNO: " + bno);

		repo.findById(bno).ifPresent(board -> model.addAttribute("vo", board));

		return "jsp/board/modify";
	}
	
	@PostMapping("/modify")
	public String modifyPost(WebBoard board, PageVO vo, RedirectAttributes rttr) {

		log.info("Modify WebBoard: " + board);

		repo.findById(board.getBno()).ifPresent(origin -> {

			origin.setTitle(board.getTitle());
			origin.setContent(board.getContent());

			repo.save(origin);
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("bno", origin.getBno());
		});

		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/board/view";
	}
	
}
