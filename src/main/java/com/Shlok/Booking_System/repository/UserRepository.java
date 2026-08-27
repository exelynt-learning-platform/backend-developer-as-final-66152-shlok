package com.Shlok.Booking_System.repository;

import com.Shlok.Booking_System.entity.User;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
}
