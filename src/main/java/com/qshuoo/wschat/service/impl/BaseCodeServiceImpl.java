package com.qshuoo.wschat.service.impl;

import java.text.DecimalFormat;
import java.util.Random;

import org.springframework.transaction.annotation.Transactional;

import com.qshuoo.wschat.service.CodeService;
import com.qshuoo.wschat.utils.WSChatResult;

@Transactional
public abstract class BaseCodeServiceImpl implements CodeService{

	@Override
	public WSChatResult sendCheckCode(String receiver) {
		String code = generateCode();
		try {
			sendCode(receiver, code);
		} catch (Exception e) {
			e.printStackTrace();
			return WSChatResult.notOk("验证码发送失败，请检查输入是否正确");
		}
		return WSChatResult.ok(code);
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
