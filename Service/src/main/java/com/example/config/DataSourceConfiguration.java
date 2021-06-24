package com.example.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DataSourceConfiguration {

	
	@Value("${covmo.database.username:covmo}")
    private String gtCovMoAlmUserName;
	
	@Value("${covmo.database.password:covmo123}")
    private String gtCovMoAlmPassword;
	
	@Value("jdbc:mysql://${covmo.database.host:192.168.3.189}:${covmo.database.port:3309}/${covmo.database.name:gt_covmo_alarm}?${covmo.database.uri:useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&useSSL=false&verifyServerCertificate=false}")
    private String gtCovMoAlmUrl;
	
	@Value("${covmo.database.alm.driver:com.mysql.jdbc.Driver}")
    private String gtCovMoAlmDriver;
	
	@Bean(name="DataSource",destroyMethod="close")
    public DataSource getDataSource() {
        HikariConfig hikariConfig = getHikariConfig();
        hikariConfig.setUsername(gtCovMoAlmUserName);
        hikariConfig.setPassword(gtCovMoAlmPassword);
        hikariConfig.setJdbcUrl(gtCovMoAlmUrl);
        hikariConfig.setDriverClassName(gtCovMoAlmDriver);
        DataSource dataSource = new HikariDataSource(hikariConfig);
        return dataSource;
    }
	
	@Bean("Hikari")
    @ConfigurationProperties(prefix="covmo.pool.hikari")
    public HikariConfig getHikariConfig() {
        HikariConfig hikariConfig = new HikariConfig();
        return hikariConfig;
    }
}
