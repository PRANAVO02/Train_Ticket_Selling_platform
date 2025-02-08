package com.ticket.brokerage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.brokerage.model.Login;
import com.ticket.brokerage.model.Ticket;
import com.ticket.brokerage.model.TicketData;
import com.ticket.brokerage.model.Train;
import com.ticket.brokerage.model.User;
import com.ticket.brokerage.service.LocationService;
import com.ticket.brokerage.service.TicketService;
import com.ticket.brokerage.service.TrainService;
import com.ticket.brokerage.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class TicketController {

	@Autowired
	TicketService ticketService;

	@Autowired
	UserService userService;

	@Autowired
	TrainService trainService;

	@Autowired
	LocationService locationService;

	@GetMapping("/post-dashboard")
	public String postDashboard(ModelMap modelMap, HttpServletRequest request) {
		Login login = (Login) request.getSession().getAttribute("LOGIN_SESSION");
		if (login == null) {
			return "redirect:/";
		}
		User user = userService.findById(login.getUserId());
		login.setWallet(user.getWalletAmount());
		modelMap.addAttribute("login", login);
		modelMap.addAttribute("tickets", ticketService.findAllByUserId(login.getUserId()));
		return "post-dashboard";
	}

	@GetMapping("/add-ticket")
	public String addTicket(ModelMap modelMap) {
		modelMap.addAttribute("ticketData", new TicketData());
		return "add-ticket";
	}

	@PostMapping("ticket-save")
	public String saveTicket(ModelMap modelMap, @ModelAttribute("ticketData") TicketData ticketData,
			HttpServletRequest request) {
		Login login = (Login) request.getSession().getAttribute("LOGIN_SESSION");
		if (login == null) {
			return "redirect:/";
		}
		Ticket ticket = new Ticket();
		ticket.setPnrNo(ticketData.getPnrNo());
		ticket.setTrain(trainService.findById(ticketData.getTrainId()));
		ticket.setStartLocation(locationService.findById(ticketData.getStartLocation()));
		ticket.setEndLocation(locationService.findById(ticketData.getEndLocation()));
		ticket.setNoOfPassengers(ticketData.getNoOfPassengers());
		ticket.setTicketCost(ticketData.getTicketCost());
		ticket.setUser(userService.findById(login.getUserId()));

		ticketService.save(ticket);
		return "redirect:/post-dashboard";
	}

	@GetMapping("/delete-ticket")
	public String deleteTicket(ModelMap modelMap, @RequestParam Long ticketId) {
		ticketService.delete(ticketId);
		return "redirect:/post-dashboard";
	}

	@ModelAttribute("trainList")
	public List<Train> initializeTrainList() {
		return trainService.allTrain();
	}
}
