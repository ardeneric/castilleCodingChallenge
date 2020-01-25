package com.coding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.entiity.Booking;

public interface BookingRepository extends JpaRepository<Booking, String> {

}
