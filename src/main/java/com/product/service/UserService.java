package com.product.service;

import com.product.entity.User;

public interface UserService {

	public User saveUser(User user);

	public boolean existEmailCheck(String email);


}
