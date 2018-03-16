package com.qshuoo.wschat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailCodeServiceImpl extends BaseCodeServiceImpl{

	@Autowired
	private JavaMailSender jMsender;
	
	// 发送方
	@Value("${spring.mail.username}")
	private String sender;
	
	// 主题
	@Value("${mail.subject}")
	private String subject;


	@Override
	public void sendCode(String receiver, String code) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(sender);
		message.setTo(receiver);
		message.setSubject(subject);
		message.setText(" 您的验证码为：" +code);
		jMsender.send(message);
	}

}
