package com.Shlok.Booking_System.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ReservationRequest {

    @NotBlank(message = "Resource ID is Required")
    private Long resourceId;

    @NotBlank(message = "Start Time is Required")
    @Future(message = "Start Time Should be in Future")
    private LocalTime startTime;

    @NotBlank(message = "End Time is Required")
    @Future(message = "End Time Should be in Future")
    private LocalTime endTime;
}
