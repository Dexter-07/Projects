package com.dexter.springboot.project.flightCheckin.integration;

import com.dexter.springboot.project.flightCheckin.integration.dto.Reservation;
import com.dexter.springboot.project.flightCheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);
}
