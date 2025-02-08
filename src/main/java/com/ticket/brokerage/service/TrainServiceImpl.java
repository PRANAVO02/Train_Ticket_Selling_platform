package com.ticket.brokerage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticket.brokerage.model.Location;
import com.ticket.brokerage.model.Train;
import com.ticket.brokerage.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	TrainRepository trainRepository;

	@Override
	public List<Train> allTrain() {
		return trainRepository.allTrain();
	}

	@Override
	public List<Location> allLocationByTrainId(Long trainId) {
		List<Location> locations = new ArrayList<>();
		Train train = trainRepository.findById(trainId).get();
		if (train != null) {
			train.getTrainLocations().forEach(trainLocation -> {
				locations.add(trainLocation.getLocation());
			});
		}
		return locations;
	}

	@Override
	public BigDecimal findTrainLocationAmount(Long trainId, Long locationId) {
		Train train = trainRepository.findById(trainId).get();
		if (train != null) {
			return train.getTrainLocations().stream().filter(l -> l.getLocation().getLocationId() == locationId)
					.findFirst().get().getAmount();
		} else {
			return BigDecimal.ZERO;
		}
	}

	@Override
	public Train findById(Long tainId) {
		return trainRepository.findById(tainId).get();
	}

}
