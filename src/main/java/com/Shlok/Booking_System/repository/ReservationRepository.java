package com.Shlok.Booking_System.repository;

import com.Shlok.Booking_System.entity.Reservation;
import com.Shlok.Booking_System.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    @Override
    Page<Reservation> findAll(Pageable pageable);

    Page<Reservation> findByUserId(Long userId,Pageable pageable);

    @Query("SELECT r FROM Reservation r WHERE " +
            "(:status IS NULL OR r.status = :status) AND " +
            "(:minPrice IS NULL OR r.price >= :minPrice) AND " +
            "(:maxPrice IS NULL OR r.price <= :maxPrice)")
    Page<Reservation> findWithFilters(@Param("status") Status status,
                                      @Param("minPrice") BigDecimal minPrice,
                                      @Param("maxPrice") BigDecimal maxPrice,
                                      Pageable pageable);
}
