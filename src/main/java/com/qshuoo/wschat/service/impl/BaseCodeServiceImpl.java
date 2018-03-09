package com.qshuoo.wschat.service.impl;

import java.text.DecimalFormat;
import java.util.Random;

import com.qshuoo.wschat.service.CodeService;

public abstract class BaseCodeServiceImpl implements CodeService{

	@Override
	public String sendCheckCode(String receiver) {
		String code = generateCode();
		sendCode(receiver, code);
		return code;
	}
	
	/**
	 * 发送验证码
	 * @param receiver
	 * @param code
	 */
	public abstract void sendCode(String receiver, String code);
	
	/**
	 * 生成4位随机数
	 * @return
	 */
	public String generateCode() {
		Random random = new Random();
		int i = random.nextInt(10000);
		String code = new DecimalFormat("0000").format(i);
		return code;
	}

}
