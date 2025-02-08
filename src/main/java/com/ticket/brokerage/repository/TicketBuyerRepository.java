package com.ticket.brokerage.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.ticket.brokerage.model.TicketBuyer;

@Repository
public interface TicketBuyerRepository extends CrudRepository<TicketBuyer, Long> {

}
