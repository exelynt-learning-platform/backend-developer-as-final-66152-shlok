package com.Shlok.Booking_System.dtos;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {

    @NotBlank(message = "Username is Required:")
    private String username;

    @NotBlank(message = "Password Is Required:")
    private String password;
}
