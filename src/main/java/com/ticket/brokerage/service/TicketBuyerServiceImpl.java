package com.ticket.brokerage.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ticket.brokerage.model.Ticket;
import com.ticket.brokerage.model.TicketBuyer;
import com.ticket.brokerage.model.TicketStatus;
import com.ticket.brokerage.model.User;
import com.ticket.brokerage.repository.TicketBuyerRepository;
import com.ticket.brokerage.repository.TicketRepository;
import com.ticket.brokerage.repository.UserRepository;

@Service
@Transactional
public class TicketBuyerServiceImpl implements TicketBuyerService {

	@Autowired
	TicketBuyerRepository ticketbuyerRepository;

	@Autowired
	TicketRepository ticketRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public void save(TicketBuyer ticketbuyer) {
		ticketbuyer.setCreateDt(new Date());
		ticketbuyerRepository.save(ticketbuyer);

		// Change ticket status as booked
		Ticket ticket = ticketRepository.findById(ticketbuyer.getTicket().getTicketId()).get();
		if (ticket != null) {
			ticket.setTicketStatus(TicketStatus.BOOKED);
			ticket.setUpdatedDt(new Date());
		}

		// Add user wallet
		User user = userRepository.findById(ticket.getUser().getUserId()).get();
		if (user != null) {
			user.setWalletAmount(user.getWalletAmount().add(ticket.getTicketCost()));
		}
	}
}
