package kr.or.ksmart.springboot34_mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.or.ksmart.springboot34_mybatis.domain.Goods;

@Controller
public class GoodsController {

	@GetMapping("/gInsert")
	public String gInsert(Goods goods) {
		System.out.println(goods.toString());
		
		return "gInsert/gInsert";
	}
	
	@PostMapping("/gInsert")
	public String gInsert(Goods goods, Model model) {
		System.out.println(goods.toString());
		
		return "gInsert/gInsert";
	}
}
