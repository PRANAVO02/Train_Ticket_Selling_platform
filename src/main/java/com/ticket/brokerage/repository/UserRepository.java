package com.ticket.brokerage.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticket.brokerage.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query(value = "SELECT U FROM User U WHERE U.userMobile=:mobile")
	public User findByMobile(@Param("mobile") String mobile);

}
