package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.service.IService;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@EnableScheduling
public class TaskConfiguration {
	
	@Autowired
	private IService service;
	
	@Scheduled(cron = "0 0 0 * * *") // 0 a.m. every day
	public void openSale() {
		service.openingSale();
	}

}
