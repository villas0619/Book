package com.example.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ReturnVO;
import com.example.service.IService;


@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private IService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ReturnVO createBook(@RequestParam String book_name) {
		return service.createBook(book_name);
	}
	
	@RequestMapping(value = "/incrSale", method = RequestMethod.GET)
	public ReturnVO increateSale(@RequestParam String book_id, @RequestParam Integer sales_volume) {
		return service.increateSale(book_id, sales_volume);
	}
	
	@RequestMapping(value = "/closOpenSale", method = RequestMethod.GET)
	public ReturnVO closOpenSale(@RequestParam boolean sale_available) {
		if(sale_available) {
			service.openingSale();
			ReturnVO vo = new ReturnVO();
			vo.set_success(true);
			return vo;
		} else {
			return service.closingSale(sale_available);
		}
	}
	

}
