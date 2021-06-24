package com.example.service.imple;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dao.IDao;
import com.example.model.Book;
import com.example.model.ReturnVO;
import com.example.service.IService;

@Service
public class ServiceImpl implements IService{
	
	@Autowired
	private IDao dao;

	@Override
	public ReturnVO createBook(String book_name) {
		Book book = dao.createBook(book_name);
		ReturnVO vo = new ReturnVO();
		vo.set_success(true);
		Map<String,String> map = new HashMap<>();
		map.put("book_id", book.getBook_id().toString());
		vo.setContent(map);
		return vo;
	}

	@Override
	public boolean searchBookName(String book_name) {
		if(dao.searchBookName(book_name)!=0)
			return false;
		else 
			return true;
	}

	@Override
	public ReturnVO increateSale(String book_id, Integer sales_volume) {
		ReturnVO vo = new ReturnVO();
		if(dao.increateSale(new Integer(book_id), sales_volume))
			vo.set_success(true);
		else
			vo.set_success(false);
		return vo;
	}


	@Override
	public ReturnVO closingSale(boolean sale_available) {
		ReturnVO vo = new ReturnVO();
		if(dao.verifySale()) {
			if(dao.closingSale(sale_available))
				vo.set_success(true);
			else
				vo.set_success(false);
		}else {
			vo.set_success(false);
		}
		return vo;
	}

	@Override
	public void openingSale() {
		dao.openingSale();
	}

}
