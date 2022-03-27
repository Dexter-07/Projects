package com.dexter.springboot.project.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dexter.springboot.project.flightReservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
