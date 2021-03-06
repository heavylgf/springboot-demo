package com.zhss.springboot.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import com.zhss.springboot.mapper.UserMapper;
import com.zhss.springboot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.zhss.springboot.domain.User;

/**
 * 用户管理模块的DAO组件的单元测试类
 * 
 * 单元测试尽量不要依赖外部，但是直到最后一层的时候，DAO层的时候，跟redis，rabbitmq打交道
 * 还是要依靠开发环境里的基础设施，来进行单元测试
 * 
 * @author liugf
 *
 */
@RunWith(SpringRunner.class) 
@SpringBootTest
@Transactional 
@Rollback(true)
public class UserDAOImplTest {
	// DAO我们直接可以连接到数据库去测试，不用去做这个mock

	/**
	 * 用户管理模块的DAO组件
	 */
	@Autowired
	private UserDAO userDAO;

	/**
	 * 测试用例：查询所有用户信息
	 */
	@Test
	public void testListUsers() {
		// 准备好mock userMapper的返回数据
		User user = new User();
		user.setName("测试用户");  
		user.setAge(20);
		// 先插入一条数据
		userDAO.saveUser(user);
		
		List<User> users = new ArrayList<User>();
		users.add(user);
		
		// 测试UserSerivce的listUsers()方法
		List<User> resultUsers = userDAO.listUsers();
		
		// 对测试结果进行断言
		assertEquals(users.size(), resultUsers.size());  
	}
	
	/**
	 * 测试用例：根据ID查询一个用户
	 */
	@Test
	public void testGetUserById() {
		User user = new User();
		user.setName("测试用户");  
		user.setAge(20);
		// 先插入一条数据
		userDAO.saveUser(user);
		
		Long userId = user.getId();
		
		User resultUser = userDAO.getUserById(userId);

		System.out.println("user.toString : ");
		System.out.println("resultUser.toString : ");

		// 这样写是不正确的，他们不是一个对象
//		assertEquals(user, resultUser);

		assertEquals(user.toString(), resultUser.toString());

	}
	
	/**
	 * 测试用例：新增用户
	 */
	@Test
	public void testSaveUser() {
		User user = new User();
		user.setName("测试用户");  
		user.setAge(20);
		// 插入一条数据
		Long resultUserId = userDAO.saveUser(user);
		// 判断这个userId 是大于0的
		assertThat(resultUserId, is(greaterThan(0L)));
	}
	
	/**
	 * 测试用例：修改用户
	 */
	@Test
	public void testUpdateUser() {
		Integer oldAge = 20;
		Integer newAge = 21;
		
		User user = new User();
		user.setName("测试用户");  
		user.setAge(oldAge);
		userDAO.saveUser(user);

		// 修改新的值
		user.setAge(newAge); 
		Boolean updateResult = userDAO.updateUser(user);
		// 返回true
		assertTrue(updateResult);

		// 查询下这个updatedUser
		User updatedUser = userDAO.getUserById(user.getId());
		// 期待是得到新的age
		assertEquals(newAge, updatedUser.getAge());
	}
	
	/**
	 * 测试用例：删除用户
	 */
	@Test
	public void testRemoveUser() {
		User user = new User();
		user.setName("测试用户");  
		user.setAge(20);
		userDAO.saveUser(user);
		
		Boolean removeResult = userDAO.removeUser(user.getId());
		
		assertTrue(removeResult);
	}
	
}
