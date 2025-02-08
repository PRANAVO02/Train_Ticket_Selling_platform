package com.ticket.brokerage.model;

import java.math.BigDecimal;

public class TicketData {

	private String pnrNo;

	private Long trainId;

	private Long startLocation;

	private Long endLocation;

	private int noOfPassengers;

	private BigDecimal ticketCost = BigDecimal.ZERO;

	public String getPnrNo() {
		return pnrNo;
	}

	public void setPnrNo(String pnrNo) {
		this.pnrNo = pnrNo;
	}

	public Long getTrainId() {
		return trainId;
	}

	public void setTrainId(Long trainId) {
		this.trainId = trainId;
	}

	public Long getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(Long startLocation) {
		this.startLocation = startLocation;
	}

	public Long getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(Long endLocation) {
		this.endLocation = endLocation;
	}

	public int getNoOfPassengers() {
		return noOfPassengers;
	}

	public void setNoOfPassengers(int noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}

	public BigDecimal getTicketCost() {
		return ticketCost;
	}

	public void setTicketCost(BigDecimal ticketCost) {
		this.ticketCost = ticketCost;
	}

}
