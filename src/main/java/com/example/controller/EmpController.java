package com.example.controller;

import java.math.BigDecimal;
import java.util.Date;

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
@RequestMapping("/emp/")
@Log
public class EmpController {

	@Autowired
	private EmpRepository repo;

	// return 없으면 이것을 논리적인 view로 봄.
	@GetMapping("/register")
	public String registerGET(@ModelAttribute("vo") Emp vo) {
		log.info("register get");
		vo.setEmpno(1111L);
		vo.setEname("홍길동");
		vo.setHiredate(new Date(00/01/1970));
		vo.setSal(new BigDecimal(100));

		return "jsp/emp/register";
	}

	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo") Emp vo, Dept dvo , RedirectAttributes rttr) {
		// 요청으로 들어온 bind된 object를 Model에 Attribute로 추가시켜준다
		// RedirectAttributes 여러 번 게시물을 등록하는 것을 방지 ( 등록 알림 처리 )
		
		log.info("register post");
		log.info("" + vo);

		vo.setDept(dvo);	//도메인 Emp클래스의 Dept 필드를통해 setDeptno,   Dept필드는 ${Dept.deptno}와 매핑돼있다.
		repo.save(vo);
		
		rttr.addFlashAttribute("msg", "success");

		return "redirect:/emp/list";
	}

	@GetMapping("/view")
	public String view(Long empno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("EMPNO: " + empno);

		repo.findById(empno).ifPresent(board -> model.addAttribute("vo", board));

		return "jsp/emp/view";

	}

	@GetMapping("/modify")
	public String modify(Long empno, @ModelAttribute("pageVO") PageVO vo, Model model) {

		log.info("MODIFY EMPNO: " + empno);

		repo.findById(empno).ifPresent(board -> model.addAttribute("vo", board));

		return "jsp/emp/modify";
	}

	@PostMapping("/modify")
	public String modifyPost(Emp emp, Dept dept, PageVO vo, RedirectAttributes rttr) {

		log.info("Modify Emp: " + emp);

		repo.findById(emp.getEmpno()).ifPresent(origin -> {

			origin.setEname(emp.getEname());
			origin.setGender(emp.getGender());
			origin.setJob(emp.getJob());
			origin.setHiredate(emp.getHiredate());
			origin.setSal(emp.getSal());
			origin.setComm(emp.getComm());
			
			origin.setDept(dept);
			
			repo.save(origin);
			
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("empno", origin.getEmpno());
		});
		

		// 페이징과 검색했던 결과로 이동하는 경우
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/emp/view";
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

		return "redirect:/emp/list";
	}

	@GetMapping("/list")
	public String list(@ModelAttribute("pageVO") PageVO vo, Model model) {

		Pageable pageable = vo.makePageable(0, "empno");
		Predicate predicate = repo.makePredicate(vo.getType(), vo.getKeyword());

		Page<Emp> result = repo.findAll(predicate, pageable);

		log.info("" + pageable);
		log.info("" + result);

		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());

//		model.addAttribute("pageMaker", new PageMaker<Dept>(result1));
		model.addAttribute("pageMaker", new PageMaker<Emp>(result));

		return "jsp/emp/list";

	}

}
