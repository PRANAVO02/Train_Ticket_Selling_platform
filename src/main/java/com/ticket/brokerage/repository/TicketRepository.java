package com.ticket.brokerage.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ticket.brokerage.model.Ticket;

@Repository
public interface TicketRepository extends CrudRepository<Ticket, Long> {

	@Query(value = "SELECT T FROM Ticket T WHERE T.user.userId=:userId AND T.ticketStatus!='DELETED'")
	List<Ticket> findAllByUserId(@Param("userId") Long userId);

	@Query(value = "SELECT T FROM Ticket T WHERE T.ticketStatus='OPEN'")
	List<Ticket> findAllOpenTicket();

}
