package com.ticket.brokerage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticket.brokerage.model.Train;

@Repository
public interface TrainRepository extends CrudRepository<Train, Long> {

	@Query(value = "SELECT T FROM Train T WHERE T.status='ACTIVE'")
	List<Train> allTrain();
	
}
