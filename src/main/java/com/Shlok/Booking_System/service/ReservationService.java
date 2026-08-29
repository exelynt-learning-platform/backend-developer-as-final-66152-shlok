package com.Shlok.Booking_System.service;

import com.Shlok.Booking_System.dtos.ReservationRequest;
import com.Shlok.Booking_System.entity.Reservation;
import com.Shlok.Booking_System.entity.Resource;
import com.Shlok.Booking_System.entity.Status;
import com.Shlok.Booking_System.entity.User;
import com.Shlok.Booking_System.repository.ReservationRepository;
import com.Shlok.Booking_System.repository.ResourceRepository;
import com.Shlok.Booking_System.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReservationService {

    private final  ReservationRepository reservationRepository;
    private final ResourceRepository resourceRepository;
    private final UserRepository userRepository;


    public ReservationService(ReservationRepository reservationRepository, ResourceRepository resourceRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.resourceRepository = resourceRepository;
        this.userRepository = userRepository;
    }

    public Reservation createReservation(String username, ReservationRequest request){
         if(request.getStartTime().isAfter(request.getEndTime())){
             throw new IllegalArgumentException("Start time must be before of End Time");
         }

         User user=userRepository.findByUsername(username)
                 .orElseThrow(()->new RuntimeException("User not found"));

        Resource resource=resourceRepository.findById(request.getResourceId())
                .orElseThrow(()->new RuntimeException("Resource not found"));

        Reservation reservation=new Reservation();
        reservation.setUser(user);
        reservation.setResource(resource);
        reservation.setStartTime(LocalDateTime.from(request.getStartTime()));
        reservation.setEndTime(LocalDateTime.from(request.getEndTime()));
        reservation.setStatus(Status.PENDING);

        return reservationRepository.save(reservation);

    }

    public Page<Reservation> getUserReservation(String username, Pageable pageable){
      User user=userRepository.findByUsername(username)
              .orElseThrow(()->new RuntimeException("User Not Found"));
        return reservationRepository.findByUserId(user.getId(), pageable);
    }
}
