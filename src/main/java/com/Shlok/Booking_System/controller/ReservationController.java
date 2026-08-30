package com.Shlok.Booking_System.controller;

import com.Shlok.Booking_System.dtos.ReservationRequest;
import com.Shlok.Booking_System.entity.Reservation;
import com.Shlok.Booking_System.entity.Status;
import com.Shlok.Booking_System.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;


    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<Reservation> createReservation(@Valid @RequestBody ReservationRequest request,
                                                         Principal principal){

        Reservation reservation=reservationService.createReservation(principal.getName(), request);
        return ResponseEntity.ok(reservation);

    }
    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Page<Reservation>> getMyReservations(
            Principal principal,
            Pageable pageable) {

        Page<Reservation> reservations = reservationService.getUserReservations(principal.getName(), pageable);
        return ResponseEntity.ok(reservations);
    }

    // 1. Admin endpoint to view all reservations and apply filters/sorting
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Reservation>> getAllReservations(
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            Pageable pageable) {

        Page<Reservation> reservations = reservationService.getAllReservationsWithFilters(status, minPrice, maxPrice, pageable);
        return ResponseEntity.ok(reservations);
    }

    // 2. Admin endpoint to change the status (PENDING, CONFIRMED, CANCELLED)
    @PutMapping("/{id}/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Reservation> updateReservationStatus(
            @PathVariable Long id,
            @RequestParam Status status) {

        Reservation updatedReservation = reservationService.updateReservationStatus(id, status);
        return ResponseEntity.ok(updatedReservation);
    }
}
