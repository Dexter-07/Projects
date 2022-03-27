package com.dexter.springboot.project.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dexter.springboot.project.flightReservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
