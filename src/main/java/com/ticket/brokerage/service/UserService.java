package com.ticket.brokerage.service;

import java.util.List;

import com.ticket.brokerage.model.User;

public interface UserService {
	public void save(User user);

	public User findById(Long userId);

	public User findByMobile(String mobile);

	public List<User> findAll();

	public void update(User user);

	public void delete(Long userId);

	public User registration(User user);

}
