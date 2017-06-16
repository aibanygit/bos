package com.aibany.bos.service;

import com.aibany.bos.domain.User;

public interface IUserService {

	public User login(User model);

	public void editPassword(String id, String password);

}
