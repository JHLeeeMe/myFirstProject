package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Dept;
import com.example.persistence.DeptRepository;
import com.example.vo.PageMaker;
import com.example.vo.PageVO;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/dept/")
@Log
public class DeptController {

	@Autowired
	private DeptRepository repo;

	// return 없으면 이것을 논리적인 view로 봄.
	@GetMapping("/register")
	public String registerGET(@ModelAttribute("vo") Dept vo) {
		log.info("register get");
		vo.setDeptno(null);
		vo.setDname("부서 이름");
		vo.setLoc("지역");

		return "jsp/dept/register";
	}

	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") Dept vo, RedirectAttributes rttr) {

		log.info("register post");
		log.info("" + vo);

		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/dept/list";
	}

	@GetMapping("/view")
	public String view(Long deptno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("DEPTNO: " + deptno);

		repo.findById(deptno).ifPresent(board -> model.addAttribute("vo", board));

		return "jsp/dept/view";

	}

	@GetMapping("/modify")
	public String modify(Long deptno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("MODIFY DEPTNO: " + deptno);

		repo.findById(deptno).ifPresent(board -> model.addAttribute("vo", board));

		return "jsp/dept/modify";
	}

	@PostMapping("/modify")
	public String modifyPost(Dept dept, PageVO vo, RedirectAttributes rttr) {

		log.info("Modify Dept: " + dept);

		repo.findById(dept.getDeptno()).ifPresent(origin -> {

			origin.setDname(dept.getDname());
			origin.setLoc(dept.getLoc());

			repo.save(origin);
			
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("deptno", origin.getDeptno());
		});

		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/dept/view";
	}

	@PostMapping("/delete")
	public String delete(Long deptno, PageVO vo, RedirectAttributes rttr) {

		log.info("DELETE DEPTNO: " + deptno);

		repo.deleteById(deptno);

		rttr.addFlashAttribute("msg", "success");

		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/dept/list";
	}

	@GetMapping("/list")
	public String list(@ModelAttribute("pageVO") PageVO vo, Model model) {

		Pageable pageable = vo.makePageable(0, "deptno");
		Predicate predicate = repo.makePredicate(vo.getType(), vo.getKeyword());

		Page<Dept> result = repo.findAll(predicate, pageable);

		log.info("" + pageable);
		log.info("" + result);

		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());

		model.addAttribute("pageMaker", new PageMaker<Dept>(result));

		return "jsp/dept/list";

	}

}
