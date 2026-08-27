package com.Shlok.Booking_System.controller;

import com.Shlok.Booking_System.dtos.AuthRequest;
import com.Shlok.Booking_System.dtos.AuthResponse;
import com.Shlok.Booking_System.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;


    public AuthController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthRequest authRequest)throws Exception{
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword())
            );
        }catch (BadCredentialsException e){
            throw new RuntimeException("Incorrect User Name or Password"+e);
        }

        final UserDetails userDetails=userDetailsService.loadUserByUsername(authRequest.getUsername());
        final String jwt=jwtUtil.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
