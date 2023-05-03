package com.example.expensetracker.service;

import com.example.expensetracker.model.DTO.ExpenseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseDto createExpense(Long id, ExpenseDto expenseDto);

    List<ExpenseDto> getAllExpenses();

    List<ExpenseDto> getUserExpenses(Long id);

    ExpenseDto getExpensesById(Long id);

    ExpenseDto updateExpense(Long id, ExpenseDto expenseDto);

    ExpenseDto updateExpenseStatus(Long id, ExpenseDto expenseDto);

    String deleteExpense(Long id);

}
