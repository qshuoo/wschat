package com.qshuoo.wschat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 程序入口
 * @author qiaoyongshuo
 *
 */
@SpringBootApplication
@ComponentScan // 启注扫描
@EnableAutoConfiguration // 启动自动配置
@EnableTransactionManagement // 启注解事务管理
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
