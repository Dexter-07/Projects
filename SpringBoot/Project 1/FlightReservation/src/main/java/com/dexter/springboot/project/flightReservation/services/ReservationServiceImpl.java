package com.dexter.springboot.project.flightReservation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dexter.springboot.project.flightReservation.dto.ReservationRequest;
import com.dexter.springboot.project.flightReservation.entities.Flight;
import com.dexter.springboot.project.flightReservation.entities.Passenger;
import com.dexter.springboot.project.flightReservation.entities.Reservation;
import com.dexter.springboot.project.flightReservation.repos.FlightRepository;
import com.dexter.springboot.project.flightReservation.repos.PassengerRepository;
import com.dexter.springboot.project.flightReservation.repos.ReservationRepository;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	FlightRepository flightRepo;
	
	@Autowired
	PassengerRepository passengerRepo;
	
	@Autowired
	ReservationRepository reservationRepo;
	
	@Override
	public Reservation bookFlight(ReservationRequest request) {
		
		Long flightId = request.getFlightId();
		Flight flight = flightRepo.findById(flightId).get();
		
		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		
		Passenger savedPassenger = passengerRepo.save(passenger);
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		Reservation savedReservation = reservationRepo.save(reservation);
		
		
		return savedReservation;
	}

}
