package com.dexter.springboot.project.flightReservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dexter.springboot.project.flightReservation.dto.ReservationUpdateRequest;
import com.dexter.springboot.project.flightReservation.entities.Reservation;
import com.dexter.springboot.project.flightReservation.repos.ReservationRepository;
import com.dexter.springboot.project.flightReservation.util.EmailUtil;

@RestController
@CrossOrigin
public class ReservationRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reservationRepo;
	
	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		
		LOGGER.info("Inside findReservation() for ID : " + id);

		Reservation reservation = reservationRepo.findById(id).get();
		return reservation;
	}
	
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		
		LOGGER.info("Inside updateReservation() for request : " + request);

		Reservation reservation = reservationRepo.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		
		Reservation updatedReservation = reservationRepo.save(reservation);
		
		LOGGER.info("Saving Reservation ..... ");

		return updatedReservation;
	}
}
