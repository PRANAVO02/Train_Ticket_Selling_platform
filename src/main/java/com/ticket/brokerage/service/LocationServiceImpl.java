package com.ticket.brokerage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.brokerage.model.Location;
import com.ticket.brokerage.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locationRepository;

	@Override
	public Location findById(Long locationId) {
		return locationRepository.findById(locationId).get();
	}

}
