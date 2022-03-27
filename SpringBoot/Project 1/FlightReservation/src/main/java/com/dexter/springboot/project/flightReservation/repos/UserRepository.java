package com.dexter.springboot.project.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dexter.springboot.project.flightReservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
