package com.dexter.springboot.project.flightReservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dexter.springboot.project.flightReservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
