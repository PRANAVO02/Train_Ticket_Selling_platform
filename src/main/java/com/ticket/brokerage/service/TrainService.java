package com.ticket.brokerage.service;

import java.math.BigDecimal;
import java.util.List;

import com.ticket.brokerage.model.Location;
import com.ticket.brokerage.model.Train;

public interface TrainService {
	
	List<Train> allTrain();
	
	List<Location> allLocationByTrainId(Long trainId);

	BigDecimal findTrainLocationAmount(Long trainId, Long locationId);
	
	Train findById(Long tainId);

}
