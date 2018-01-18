package com.qshuoo.wschat.test;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.qshuoo.wschat.pojo.Message;

public class JSONTest {
	
	@Test
	public void test01() {
		String json = "{\"from\":\"456\",\"to\":\"123\",\"msg\":\"hi\"}";
		System.out.println(JSON.parseObject(json, Message.class).getMsg());
	}

}
