package com.qshuoo.wschat.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.qshuoo.wschat.Application;
import com.qshuoo.wschat.dao.impl.UserDaoImpl;
import com.qshuoo.wschat.pojo.User;

/**
 * 
 * @author qiaoyongshuo
 *
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class UserDaoTest {
	
	@Autowired
	private UserDaoImpl dao;
	

}
