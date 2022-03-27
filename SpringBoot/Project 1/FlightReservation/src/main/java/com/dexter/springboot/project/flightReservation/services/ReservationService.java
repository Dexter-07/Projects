package com.dexter.springboot.project.flightReservation.services;

import com.dexter.springboot.project.flightReservation.dto.ReservationRequest;
import com.dexter.springboot.project.flightReservation.entities.Reservation;

public interface ReservationService {
	
	public Reservation bookFlight(ReservationRequest request);
}
