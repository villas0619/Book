package com.example.Dao;

import com.example.model.Book;

public interface IDao {
	
	Book createBook(String book_name);
	int searchBookName(String book_name);
	boolean increateSale(Integer book_id, Integer sales_volume);
	boolean verifySale();
	boolean closingSale(boolean sale_available);
	void openingSale();

}
