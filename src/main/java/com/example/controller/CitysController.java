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

import com.example.domain.City;
import com.example.domain.Country;
import com.example.persistence.CityRepository;
import com.example.vo.PageMaker;
import com.example.vo.PageVO;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Controller //controller로 동작한다.
@RequestMapping("/citys/")  // mapping
@Log
public class CitysController {

	
	@Autowired  // 타입을 이용하여 의존하는 객체를 삽입해주는 역할
	private CityRepository repo;
	
	@GetMapping("/register")
	public String registerGET(@ModelAttribute("vo")City vo, Country cvo ){   
		// 요청으로 들어온 bind된 object를 Model에 Attribute로 추가시켜준다

		log.info("register get");
		
		vo.setId(111);
		vo.setName("seoul");
		vo.setDistrict("seoul");
		vo.setPopulation(1234567);
		
		return "thymeleaf/citys/register";
				
	}
	
	@PostMapping("/register")
	public String registerPOST(@ModelAttribute("vo")City vo, RedirectAttributes rttr){
		// 요청으로 들어온 bind된 object를 Model에 Attribute로 추가시켜준다
		// RedirectAttributes 여러 번 게시물을 등록하는 것을 방지 ( 등록 알림 처리 )  
		
		log.info("register post");
		log.info("" + vo);
		
//		vo.setCountry(cvo);
		repo.save(vo);
		rttr.addFlashAttribute("msg", "success"); // (등록 알림 처리)
		
		return "redirect:/citys/list"; // 작성이 완료되면 리스트로 돌려준다.
	}
	
	@GetMapping("/view")
	public String view(Integer id, @ModelAttribute("pageVO") PageVO vo, Model model){
		
		log.info("ID: "+ id);
		
		repo.findById(id).ifPresent(city -> model.addAttribute("vo", city));
		
		return "thymeleaf/citys/view";
	}
	
	@GetMapping("/modify")
	public String modify(Integer id, @ModelAttribute("pageVO") PageVO vo, Model model){
		
		log.info("MODIFY ID: "+ id);
		
		repo.findById(id).ifPresent(city -> model.addAttribute("vo", city));
		
		return "thymeleaf/citys/modify";
	}
	
	@PostMapping("/modify")
	public String modifyPost(City city, Country cvo, PageVO vo, RedirectAttributes rttr ){
		
		log.info("Modify CITY: " + city);
		
		repo.findById(city.getId()).ifPresent( origin ->{
			
			origin.setId(city.getId());
			origin.setName(city.getName());
			origin.setDistrict(city.getDistrict());
			origin.setPopulation(city.getPopulation());
			origin.setCountry(cvo);
			
			repo.save(origin);
			rttr.addFlashAttribute("msg", "success");
			rttr.addAttribute("id", origin.getId());
		});
//		log.info("code" + vo1.getCountry());
//		log.info("code" + vo2.getCode());
		
		//페이징과 검색했던 결과로 이동하는 경우 
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/citys/view";
	}
	
	
	@PostMapping("/delete")
	public String delete(Integer id, PageVO vo, RedirectAttributes rttr ){
		
		log.info("DELETE CITY: " + id);
		
		repo.deleteById(id);
		
		rttr.addFlashAttribute("msg", "success");

		// 페이징과 검색했던 결과로 이동하는 경우 
		rttr.addAttribute("page", vo.getPage());
		rttr.addAttribute("size", vo.getSize());
		rttr.addAttribute("type", vo.getType());
		rttr.addAttribute("keyword", vo.getKeyword());

		return "redirect:/citys/list";
	}
	
	// 검색 처리
	@GetMapping("/list")
	public String list(@ModelAttribute("pageVO") PageVO vo, Model model){
		// 요청으로 들어온 bind된 object를 Model에 Attribute로 추가시켜준다
		
		Pageable pageable = vo.makePageable(0, "id");
		Predicate predicate = repo.makePredicate(vo.getType(), vo.getKeyword());
		
//		Page<WebBoard> result = repo.findAll(
//				repo.makePredicate(vo.getType(), 
//						           vo.getKeyword()), page);
		Page<City> result = repo.findAll(predicate, pageable);
		
		log.info(""+ pageable);
		log.info(""+result);
		
		log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
		
		
		model.addAttribute("pageMaker", new PageMaker<City>(result));
		
		return "thymeleaf/citys/list";
		
	}

}



//ver 1
//@GetMapping("/list")
//public void list(
//		@PageableDefault(
//				direction=Sort.Direction.DESC, 
//				sort="bno" , 
//				size=10 , 
//				page = 0) Pageable page , Model model ) {
//
//	log.info("list() called..." + page);
//	
//	Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
//
//	PageMaker<WebBoard> pgMaker = new PageMaker<>(result.getContent(), 
//			result.getTotalPages(), 
//			result.getPageable());
//	
//	
//	log.info(""+pgMaker);
//	
//	model.addAttribute("result", pgMaker);
//
//}

//ver 2
//@GetMapping("/list")
//public void list(PageVO vo, Model model){
//	
//	Pageable page = vo.makePageable(0, "bno");
//	
//	Page<WebBoard> result = repo.findAll(repo.makePredicate(null, null), page);
//	
//	log.info(""+ page);
//	log.info(""+result);
//	
//	log.info("TOTAL PAGE NUMBER: " + result.getTotalPages());
//	
//	
//	model.addAttribute("result", new PageMaker(result));
//			
//}


