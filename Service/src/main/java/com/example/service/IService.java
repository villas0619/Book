package com.example.service;

import com.example.model.ReturnVO;

public interface IService {
	
	ReturnVO createBook(String book_name);
	boolean searchBookName(String book_name);
	ReturnVO increateSale(String book_id, Integer sales_volume);
	ReturnVO closingSale(boolean sale_available);
	void openingSale();
}
