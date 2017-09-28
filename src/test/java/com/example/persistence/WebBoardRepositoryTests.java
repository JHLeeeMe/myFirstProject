package com.example.persistence;

import java.util.Arrays;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.Describable;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.WebBoard;
import com.example.vo.PageMaker;
import com.example.vo.PageVO;
import com.querydsl.core.types.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebBoardRepositoryTests {

	@Inject
	WebBoardRepository repo;

	@Test
	public void test() {
		// 전체조회
		System.out.println(repo);
		repo.findAll().forEach(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void test2() {
		System.out.println(repo);

		PageRequest pageable = PageRequest.of(1, 10);

		repo.findAll(pageable).forEach(e -> {
			System.out.println(e);
		});

	}

	@Test
	public void test3() {
		System.out.println(repo);

		PageRequest pageable = PageRequest.of(0, 5, Sort.Direction.DESC, "bno");

		repo.findAll(pageable).forEach(e -> System.out.println(e));
	}

	@Test
	public void test4() {
		System.out.println(repo);

		// controller에서 자동으로 바인딩 됨.
		// boards/list?page=0&size=20
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setSize(20);

		System.out.println(pageVO);

		PageRequest pageable = PageRequest.of(pageVO.getPage() - 1, pageVO.getSize(), Direction.DESC, "bno");

		repo.findAll(pageable).forEach(e -> System.out.println(e));
	}

	@Test
	public void test5() {
		System.out.println(repo);

		// controller에서 자동으로 바인딩 됨.
		// boards/list?page=1&size=20
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setSize(20);

		System.out.println(pageVO);

		repo.findAll(pageVO.makePageable(0, "bno")).forEach(e -> System.out.println(e));
	}

	@Test
	public void test6() {
		System.out.println(repo);

		// controller에서 자동으로 바인딩 됨.
		// boards/list?page=1&size=20&type=t&keyword=99
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setSize(20);
		pageVO.setType("w");
		pageVO.setKeyword("9");

		System.out.println(pageVO);

		Pageable pageable = pageVO.makePageable(0, "bno");
		Predicate predicate = repo.makePredicate(pageVO.getType(), pageVO.getKeyword());

		repo.findAll(predicate, pageable).forEach(e -> {
			System.out.println(e);
		});
	}

	@Test
	public void test7() {
		System.out.println(repo);

		// controller에서 자동으로 바인딩 됨.
		// boards/list?page=1&size=10
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);
		pageVO.setSize(5);
		pageVO.setType("w");
		pageVO.setKeyword("9");

		Pageable pageable = pageVO.makePageable(0, "bno");
		Predicate predicate = repo.makePredicate(pageVO.getType(), pageVO.getKeyword());
		Page<WebBoard> pageInfo = repo.findAll(predicate, pageable);
		PageMaker<WebBoard> pageMaker = new PageMaker<>(pageInfo);

		repo.findAll(predicate, pageable)
		.forEach(e -> {
			System.out.println(e);
		});
		
		//2page를 웹에 출력, 웹화면
//		for (WebBoard b : pageMaker.getResult().getContent()) {
//			System.out.println(b);
//		}
		
		
		System.out.println("##########################");
		
		if(pageMaker.getPrevPage() != null) {
			System.out.print("Prev " + (pageMaker.getPrevPage().getPageNumber() + 1));
		}
		
		for(Pageable p : pageMaker.getPageList()) {
			System.out.print(" " + (p.getPageNumber() + 1));
		}
		
		if (pageMaker.getNextPage() != null) {
			System.out.print(" Next " + (pageMaker.getNextPage().getPageNumber() + 1));
		}
		System.out.println();
	}
}
