package com.ticket.brokerage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ticket.brokerage.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {

}
