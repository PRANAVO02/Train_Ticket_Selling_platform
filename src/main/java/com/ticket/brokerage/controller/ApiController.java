package com.ticket.brokerage.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ticket.brokerage.model.Location;
import com.ticket.brokerage.service.TrainService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	TrainService trainService;

	@GetMapping("/location-search")
	public List<Location> locationSearch(@RequestParam Long trainId) {
		return trainService.allLocationByTrainId(trainId);
	}

	@GetMapping("/tain-location-amount")
	public BigDecimal tainLocationAmount(@RequestParam Long trainId, @RequestParam Long locationId) {
		return trainService.findTrainLocationAmount(trainId, locationId);
	}

}
