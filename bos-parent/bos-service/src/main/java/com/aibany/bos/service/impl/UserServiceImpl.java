package com.aibany.bos.service.impl;

import com.aibany.bos.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aibany.bos.dao.IUserDao;
import com.aibany.bos.domain.User;
import com.aibany.bos.service.IUserService;


@Service
@Transactional  /*事务注解*/
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userDao;
	/***
	 * 用户登录
	 */
	public User login(User user) {
		//使用MD5加密密码
		String password = MD5Utils.md5(user.getPassword());
		return userDao.findUserByUsernameAndPassword(user.getUsername(),password);
	}

	@Override
	public void editPassword(String id, String password) {
		password = MD5Utils.md5(password);
		userDao.executeUpdate("user.editpassword",password,id);
	}
}
