package com.example.expensetracker.repository;

import com.example.expensetracker.model.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserName(String username);

    Optional<Users> findById(Long id);

}

