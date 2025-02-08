package com.ticket.brokerage.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.brokerage.model.Status;
import com.ticket.brokerage.model.User;
import com.ticket.brokerage.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(User user) {
		user.setWalletAmount(BigDecimal.ZERO);
		user.setStatus(Status.ACTIVE);
		user.setCreateDt(new Date());
		userRepository.save(user);
	}

	@Override
	public User findById(Long userId) {
		return userRepository.findById(userId).get();
	}

	@Override
	public User findByMobile(String mobile) {
		return userRepository.findByMobile(mobile);
	}

	@Override
	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	@Override
	public void update(User user) {
		User entity = userRepository.findById(user.getUserId()).get();
		if (entity != null) {
			entity.setUserName(user.getUserName());
		}
	}

	@Override
	public void delete(Long userId) {
		User entity = userRepository.findById(userId).get();
		if (entity != null) {
			entity.setStatus(Status.INACTIVE);
		}
	}

	@Override
	public User registration(User user) {
		user.setWalletAmount(BigDecimal.ZERO);
		user.setStatus(Status.ACTIVE);
		user.setCreateDt(new Date());
		return userRepository.save(user);
	}
}
