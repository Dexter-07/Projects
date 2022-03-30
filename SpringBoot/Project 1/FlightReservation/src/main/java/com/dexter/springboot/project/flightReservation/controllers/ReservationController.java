package com.dexter.springboot.project.flightReservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dexter.springboot.project.flightReservation.dto.ReservationRequest;
import com.dexter.springboot.project.flightReservation.entities.Flight;
import com.dexter.springboot.project.flightReservation.entities.Reservation;
import com.dexter.springboot.project.flightReservation.repos.FlightRepository;
import com.dexter.springboot.project.flightReservation.services.ReservationService;

@Controller
public class ReservationController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationController.class);

	
	@Autowired
	private FlightRepository flightRepo;
	
	@Autowired
	ReservationService reservationService;
	
	
	@RequestMapping(value="/showCompleteReservation")
	public String showCompleteReservation(@RequestParam Long flightId, ModelMap modelMap) {
		LOGGER.info("Inside showCompleteReservation() for the flight ID : " +flightId);

		
		Flight flight = flightRepo.findById(flightId).get();
		modelMap.addAttribute("flight", flight);
		
		LOGGER.info("Flight is : " +flight);

		return "completeReservation";
	}
	
	@RequestMapping(value="/completeReservation", method=RequestMethod.POST)
	public String completeReservation(ReservationRequest request, ModelMap modelMap) {
		
		LOGGER.info("Inside completeReservation() "+ request);

		
		Reservation reservation = reservationService.bookFlight(request);
		modelMap.addAttribute("msg" ,"Reservation created successfully and the id is "+ reservation.getId());
		return "reservationConfirmation";
		
	}
}
