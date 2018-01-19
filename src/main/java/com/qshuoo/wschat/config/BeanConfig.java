package com.qshuoo.wschat.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 配置Bean
 * @author qiaoyongshuo
 *
 */
@Configuration
public class BeanConfig {

	/**
	 * 配置websocket
	 * @return
	 */
	@Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
	
	/**
	 * 配置fastJson
	 * @return
	 */
	@Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        FastJsonHttpMessageConverter fastConvert = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConvert.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters((HttpMessageConverter<?>) fastConvert);
    }
	
	/**
	 * 配置数据源
	 * @return
	 */
	@Primary
    @Bean(name = "firstDataSource")
    @Qualifier("firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.first")
    public DataSource firstDataSource() {
		return DataSourceBuilder.create().build();
    }
	
	/**
	 * 注入 jdbc
	 * @param dataSource
	 * @return
	 */
	@Bean(name = "jdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("firstDataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
    }

	/**
	 * 注入NamedJdbc
	 * @param dataSource
	 * @return
	 */
    @Bean(name = "firstNamedJdbcTemplate")
    public NamedParameterJdbcTemplate firstNamedJdbcTemplate(@Qualifier("firstDataSource") DataSource dataSource) {
    	return new NamedParameterJdbcTemplate(dataSource);
    }
}
