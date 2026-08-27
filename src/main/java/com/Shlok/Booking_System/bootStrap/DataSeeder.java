package com.Shlok.Booking_System.bootStrap;

import com.Shlok.Booking_System.entity.Role;
import com.Shlok.Booking_System.entity.User;
import com.Shlok.Booking_System.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if(userRepository.count()==0){
            User admin=new User();

            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);

            User standardUser=new User();
            standardUser.setUsername("Shlok");
            standardUser.setPassword(passwordEncoder.encode("Shlok6123"));
            standardUser.setRole(Role.ADMIN);

            userRepository.save(standardUser);

            System.out.println("Seed Users are Saved Succesfully");
        }

    }
}
