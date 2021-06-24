package com.example.Dao.sql;

public enum DaoSql {

	CREATE_BOOK(" INSERT INTO `book`.`book`(`book_name`) VALUES (:book_name) "),
	SEARCH_NAME(" SELECT COUNT(book_name) FROM `book`.`book` WHERE book_name =:book_name"),
	MODIFY_BOOK(" UPDATE `book`.`book` SET `sales_volume`=sales_volume+:sales_volume WHERE `book_id`=:book_id "),
	VERIFY_SALE(" SELECT sale_available FROM `book`.`controlsale` "),
	COLSING_SALE(" UPDATE `book`.`controlsale` SET sale_available=0 WHERE (DATE_ADD(closing_expired, INTERVAL 7 DAY) <:closing_expired) "),
	OPEN_SALE(" UPDATE `book`.`controlsale` SET sale_available=0 "),
	;
	
	private String sql;

	DaoSql(String sql){
		this.sql = sql;
	}
	
	public String toString()
	{
		return sql ;
	}
}
