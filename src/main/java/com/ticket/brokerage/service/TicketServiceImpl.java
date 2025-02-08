package com.ticket.brokerage.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.brokerage.model.Ticket;
import com.ticket.brokerage.model.TicketStatus;
import com.ticket.brokerage.repository.TicketRepository;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketRepository ticketRepository;
	
	@Override
	public void save(Ticket ticket) {
		ticket.setTicketStatus(TicketStatus.OPEN);
		ticket.setCreateDt(new Date());
		ticketRepository.save(ticket);
	}

	@Override
	public List<Ticket> findAllByUserId(Long userId) {
		return ticketRepository.findAllByUserId(userId);
	}

	@Override
	public List<Ticket> findAllOpenTicket() {
		return ticketRepository.findAllOpenTicket();
	}

	@Override
	public void delete(Long ticketId) {
		Ticket entity = ticketRepository.findById(ticketId).get();
		if (entity != null) {
			entity.setTicketStatus(TicketStatus.DELETED);
			entity.setUpdatedDt(new Date());
		}
	}

	@Override
	public Ticket findById(Long ticketId) {
		return ticketRepository.findById(ticketId).get();
	}
}
