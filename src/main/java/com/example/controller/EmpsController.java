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
import com.example.domain.Emp;
import com.example.persistence.DeptRepository;
import com.example.persistence.EmpRepository;
import com.example.vo.PageMaker;
import com.example.vo.PageVO;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/emps/")
@Log
public class EmpsController {

	@Autowired
	private EmpRepository repo;
	@Autowired
	private DeptRepository deptRepo;

	// return 없으면 이것을 논리적인 view로 봄.
	@GetMapping("/register")
	public String registerGET(@ModelAttribute("vo") Emp vo) {
		log.info("register get");
		vo.setEmpno(10L);
		vo.setEname("홍길동");

		return "thymeleaf/emps/register";
	}

	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") Emp vo, RedirectAttributes rttr) {

		log.info("register post");
		log.info("" + vo);

		repo.save(vo);
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/emps/list";
	}

	@GetMapping("/view")
	public String view(Long empno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("EMPNO: " + empno);

		repo.findById(empno).ifPresent(board -> model.addAttribute("vo", board));

		return "thymeleaf/emps/view";

	}

	@GetMapping("/modify")
	public String modify(Long empno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("MODIFY EMPNO: " + empno);

		repo.findById(empno).ifPresent(board -> model.addAttribute("vo", board));

		return "thymeleaf/emps/modify";
	}

	@PostMapping("/modify")
	public String modifyPost(Emp emp, PageVO vo, RedirectAttributes rttr) {

		log.info("Modify Emp: " + emp);

		repo.findById(emp.getEmpno()).ifPresent(origin -> {

			origin.setEname(emp.getEname());
			origin.setGender(emp.getGender());
			origin.setJob(emp.getJob());
			origin.setHiredate(emp.getHiredate());
			origin.setSal(emp.getSal());
			origin.setComm(emp.getComm());
			
			repo.save(origin);
			
			
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("empno", origin.getEmpno());
		});
		

		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/emps/view";
	}

	@PostMapping("/delete")
	public String delete(Long empno, PageVO vo, RedirectAttributes rttr) {

		log.info("DELETE EMPNO: " + empno);

		repo.deleteById(empno);

		rttr.addFlashAttribute("msg", "success");

		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/emps/list";
	}

	@GetMapping("/list")
	public String list(@ModelAttribute("pageVO") PageVO vo, Model model) {

		Pageable pageable = vo.makePageable(0, "empno");
		Predicate predicate = repo.makePredicate(vo.getType(), vo.getKeyword());

		Page<Emp> result = repo.findAll(predicate, pageable);

		log.info("" + pageable);
		log.info("" + result);

		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());

		model.addAttribute("pageMaker", new PageMaker<Emp>(result));

		return "thymeleaf/emps/list";

	}

}
