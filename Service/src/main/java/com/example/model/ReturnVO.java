package com.example.model;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ReturnVO implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean is_success;
	
	@JsonInclude(com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL)
	private Map<String,String> content;

}
