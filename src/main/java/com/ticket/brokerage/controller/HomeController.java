package com.ticket.brokerage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.brokerage.model.Login;
import com.ticket.brokerage.model.Registration;
import com.ticket.brokerage.model.User;
import com.ticket.brokerage.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String homePage() {
		return "home";
	}

	@GetMapping("/login")
	public String login(ModelMap modelMap, @RequestParam(name = "type", required = false) String type) {
		if (type == null) {
			return "redirect:/";
		}
		Login login = new Login();
		login.setType(type);
		modelMap.addAttribute("login", login);
		return "login";
	}

	@PostMapping("/login")
	public String login(ModelMap modelMap, @ModelAttribute("login") Login login, HttpServletRequest request) {
		User user = userService.findByMobile(login.getMobile());
		if (user == null) {
			login.setErrorMsg("Invalid mobile number. Please register your mobile number.");
			modelMap.addAttribute("login", login);
			return "login";
		}

		if (!user.getUserPassword().equals(login.getPassword())) {
			login.setErrorMsg("Invalid password. Please enter the correct password.");
			modelMap.addAttribute("login", login);
			return "login";
		}
		login.setUserName(user.getUserName());
		login.setUserId(user.getUserId());

		request.getSession().setAttribute("LOGIN_SESSION", login);
		if (login.getType().equalsIgnoreCase("post")) {
			return "redirect:/post-dashboard";
		} else {
			return "redirect:/buyer-dashboard";
		}
	}

	@GetMapping("/register")
	public String registration(ModelMap modelMap) {
		modelMap.addAttribute("registration", new Registration());
		return "registration";
	}

	@PostMapping("/register")
	public String registration(ModelMap modelMap, @ModelAttribute("registration") Registration registration) {
		User existingUser = userService.findByMobile(registration.getUserMobile());
		if (existingUser != null) {
			modelMap.addAttribute("errorMsg",
					"This mobile number is already registered with us. Please use a different mobile number for registration.");
			modelMap.addAttribute("registration", registration);
			return "registration";
		}

		if (!registration.getPassword().equals(registration.getConfirmPassword())) {
			modelMap.addAttribute("errorMsg", "The passwords are mismatch, please re-enter the password");
			modelMap.addAttribute("registration", registration);
			return "registration";
		}

		User user = new User();
		user.setUserName(registration.getUserName());
		user.setUserMobile(registration.getUserMobile());
		user.setUserPassword(registration.getPassword());
		user.setUserEmail(registration.getUserEmail());

		userService.save(user);
		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		Login login = (Login) request.getSession().getAttribute("LOGIN_SESSION");
		if (login != null) {
			request.getSession().removeAttribute("LOGIN_SESSION");
		}
		return "redirect:/";
	}

}
