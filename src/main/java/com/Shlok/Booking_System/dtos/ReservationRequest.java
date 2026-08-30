package com.Shlok.Booking_System.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ReservationRequest {

    @NotBlank(message = "Resource ID is Required")
    private Long resourceId;

    @NotBlank(message = "Start Time is Required")
    @Future(message = "Start Time Should be in Future")
    private LocalTime startTime;


    @NotNull(message = "Price is required")
    private java.math.BigDecimal price;

    @NotBlank(message = "End Time is Required")
    @Future(message = "End Time Should be in Future")
    private LocalTime endTime;
}
