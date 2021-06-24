package com.example.Dao.impl;

import java.beans.Transient;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.Dao.IDao;
import com.example.Dao.sql.DaoSql;
import com.example.model.Book;

@Repository("Dao")
public class DaoImpl extends NamedParameterJdbcDaoSupport implements IDao{

	@Resource( name = "DataSource" )
    protected void initDataSource ( DataSource dataSource )
    {
        super.setDataSource ( dataSource ) ;
    }
	
	@Override
	public Book createBook(String book_name) {
		 SqlParameterSource params = new MapSqlParameterSource()
	                .addValue("book_name", book_name);
		 KeyHolder keyHolder = new GeneratedKeyHolder();
		 int insertRow = getNamedParameterJdbcTemplate().update(DaoSql.CREATE_BOOK.toString(),params, keyHolder);
		if (insertRow != 1) {
			throw new RuntimeException("Create book Fail. ");
		}
		int id = keyHolder.getKey().intValue();
		Book newBook = new Book();
		newBook.setBook_id(id);
		newBook.setBook_name(book_name);
		return newBook;
	}

	@Override
	public int searchBookName(String book_name) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("book_name", book_name);
		String sql = DaoSql.SEARCH_NAME.toString();
		int count = getNamedParameterJdbcTemplate().queryForObject(sql, params, Integer.class);
		return count;
	}

	@Override
	@Transient
	public boolean increateSale(Integer book_id, Integer sales_volume) {
		SqlParameterSource params = new MapSqlParameterSource().addValue("book_id", book_id).addValue("sales_volume",
				sales_volume);
		String sql = DaoSql.MODIFY_BOOK.toString();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int updateRow = getNamedParameterJdbcTemplate().update(sql, params, keyHolder);
		if (updateRow != 1) {
			return false;
		}
		return true;
	}

	@Override
	@Transient
	public boolean closingSale(boolean sale_available) {
		SqlParameterSource params = new MapSqlParameterSource()
				.addValue("sale_available", sale_available);
		String sql = DaoSql.COLSING_SALE.toString();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		int updateRow = getNamedParameterJdbcTemplate().update(sql, params, keyHolder);
		if (updateRow != 1) {
			return false;
		}
		return true;
	}

	@Override
	public boolean verifySale() {
		SqlParameterSource params = new MapSqlParameterSource();
		String sql = DaoSql.VERIFY_SALE.toString();
		int count = getNamedParameterJdbcTemplate().queryForObject(sql, params, Integer.class);
		if(count==1)
			return true;
		else
			return false;
	}

	@Override
	@Transient
	public void openingSale() {
		SqlParameterSource params = new MapSqlParameterSource();
		String sql = DaoSql.OPEN_SALE.toString();
		KeyHolder keyHolder = new GeneratedKeyHolder();
		@SuppressWarnings("unused")
		int updateRow = getNamedParameterJdbcTemplate().update(sql, params, keyHolder);
	}

}
