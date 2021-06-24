package com.example.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Book implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer book_id;
	
	private String book_name;
	
	private Integer sales_volume;
	
}
