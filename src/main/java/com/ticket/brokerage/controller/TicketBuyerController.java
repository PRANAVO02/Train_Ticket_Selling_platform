package com.ticket.brokerage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.brokerage.model.Login;
import com.ticket.brokerage.model.Ticket;
import com.ticket.brokerage.model.TicketBuyer;
import com.ticket.brokerage.service.TicketBuyerService;
import com.ticket.brokerage.service.TicketService;
import com.ticket.brokerage.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TicketBuyerController {

	@Autowired
	TicketService ticketService;

	@Autowired
	TicketBuyerService ticketBuyerService;

	@Autowired
	UserService userService;

	@GetMapping("/buyer-dashboard")
	public String buyerDashboard(ModelMap modelMap, HttpServletRequest request) {
		Login login = (Login) request.getSession().getAttribute("LOGIN_SESSION");
		if (login == null) {
			return "redirect:/";
		}

		modelMap.addAttribute("login", login);
		modelMap.addAttribute("tickets", ticketService.findAllOpenTicket());
		return "buyer-dashboard";
	}

	@GetMapping("/book-ticket")
	public String bookTicket(ModelMap modelMap, @RequestParam Long ticketId, HttpServletRequest request) {
		Login login = (Login) request.getSession().getAttribute("LOGIN_SESSION");
		if (login == null) {
			return "redirect:/";
		}

		TicketBuyer ticketBuyer = new TicketBuyer();
		Ticket ticket = ticketService.findById(ticketId);
		ticketBuyer.setTicket(ticket);
		ticketBuyer.setUser(userService.findById(login.getUserId()));
		ticketBuyerService.save(ticketBuyer);

		modelMap.addAttribute("login", login);
		modelMap.addAttribute("ticket", ticket);
		return "confirmation";
	}

}
