package com.dexter.springboot.project.flightReservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dexter.springboot.project.flightReservation.dto.ReservationRequest;
import com.dexter.springboot.project.flightReservation.entities.Flight;
import com.dexter.springboot.project.flightReservation.entities.Passenger;
import com.dexter.springboot.project.flightReservation.entities.Reservation;
import com.dexter.springboot.project.flightReservation.repos.FlightRepository;
import com.dexter.springboot.project.flightReservation.repos.PassengerRepository;
import com.dexter.springboot.project.flightReservation.repos.ReservationRepository;
import com.dexter.springboot.project.flightReservation.util.EmailUtil;
import com.dexter.springboot.project.flightReservation.util.PDFGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {
	
	@Value("${com.dexter.flightReservation.itinerary.path}")
	private String ITINERARY_DIR;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);

	@Autowired
	FlightRepository flightRepo;

	@Autowired
	PassengerRepository passengerRepo;

	@Autowired
	ReservationRepository reservationRepo;

	@Autowired
	PDFGenerator pdfGenerator;

	@Autowired
	EmailUtil emailUtil;

	@Override
	public Reservation bookFlight(ReservationRequest request) {

		LOGGER.info("Inside bookFlight()");

		Long flightId = request.getFlightId();
		Flight flight = flightRepo.findById(flightId).get();

		LOGGER.info("Fetching flight for FLIGHT ID: " + flightId);

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setPhone(request.getPassengerPhone());
		passenger.setEmail(request.getPassengerEmail());
		LOGGER.info("Passenger is :  " + passenger);

		Passenger savedPassenger = passengerRepo.save(passenger);

		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);
		Reservation savedReservation = reservationRepo.save(reservation);

		LOGGER.info("Saving the Reservation: " + reservation);

		String filePath = ITINERARY_DIR + savedReservation.getId() + ".pdf";

		LOGGER.info("Generating the itinerary...");

		pdfGenerator.generateItinerary(savedReservation, filePath);

		LOGGER.info("Sending the mail ...");

		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
