package com.example.expensetracker.repository;

import com.example.expensetracker.model.entity.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expenses, Long> {

    Optional<List<Expenses>> findByUserId(Long id);

    Optional<List<Expenses>> findByCategoryId(Long id);

}