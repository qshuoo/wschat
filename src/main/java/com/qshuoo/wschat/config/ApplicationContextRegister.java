package com.qshuoo.wschat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy(false)
public class ApplicationContextRegister implements ApplicationContextAware {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextRegister.class);
	
	private static ApplicationContext APPLICATION_CONTEXT; // spring 上下文

	/**
	 * 设置spring上下文 
	 * @param applicationContext spring上下文 
	 * @throws
	 * BeansException
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		LOGGER.info("ApplicationContext registed-->{}", applicationContext);
		APPLICATION_CONTEXT = applicationContext;
	}

	public static ApplicationContext getApplicationContext() {
		return APPLICATION_CONTEXT;
	}
}
