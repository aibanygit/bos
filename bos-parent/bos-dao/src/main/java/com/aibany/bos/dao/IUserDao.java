package com.aibany.bos.dao;

import com.aibany.bos.dao.base.IBaseDao;
import com.aibany.bos.domain.User;

public interface IUserDao extends IBaseDao<User> {

	public User findUserByUsernameAndPassword(String username, String password);

}
