package com.ticket.brokerage.service;

import java.util.List;

import com.ticket.brokerage.model.Ticket;

public interface TicketService {

	public void save(Ticket ticket);

	public List<Ticket> findAllByUserId(Long userId);

	public void delete(Long ticketId);

	public List<Ticket> findAllOpenTicket();

	public Ticket findById(Long ticketId);

}
