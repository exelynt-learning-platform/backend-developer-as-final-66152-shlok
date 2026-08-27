package com.Shlok.Booking_System.repository;

import com.Shlok.Booking_System.entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Override
    Page<Reservation> findAll(Pageable pageable);

    Page<Reservation> findByUserId(Long userId,Pageable pageable);
}
