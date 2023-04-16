package com.example.expensetracker.service;

import com.example.expensetracker.model.DTO.ExpenseDto;
import com.example.expensetracker.model.entity.Expenses;
import com.example.expensetracker.model.entity.Users;
import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.model.mapper.ExpenseMapper;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService{

    private final ExpenseRepository expenseRepository;

    private final UserRepository userRepository;

    private final ExpenseMapper expenseMapper;


    @Override
    public ExpenseDto createExpense(Long id, ExpenseDto expenseDto){

        Users user = userRepository.findById(id).orElseThrow(() -> new BadRequestException("User not found!"));

        Expenses expense = new Expenses();
        expense.setUser(user);
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(expenseDto.getCategory());
        expense.setPrice(expenseDto.getPrice());
        expense.setStatus(expenseDto.getStatus());
        expense.setPaidBy(expenseDto.getPaidBy());
        Expenses savedExpense = expenseRepository.save(expense);

        return expenseMapper.toDto(savedExpense);

    }

    @Override
    public List<ExpenseDto> getAllExpenses(){
        List<ExpenseDto> expenseDtos = expenseRepository.findAll().stream().map(expenses -> expenseMapper.toDto(expenses))
                .collect(Collectors.toList());

        return expenseDtos;
    }

    @Override
    public List<ExpenseDto> getUserExpenses(Long id){
        List<Expenses> expenses = expenseRepository.findByUserId(id).orElseThrow(()-> new BadRequestException("ToDo not found!"));
        List<ExpenseDto> result = expenses.stream().map(e -> expenseMapper.toDto(e))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public ExpenseDto getExpensesById(Long id){
        Expenses expenses = expenseRepository.findById(id).orElseThrow(() -> new BadRequestException("Expense not found!"));
        return expenseMapper.toDto(expenses);
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto){
        Expenses existingExpense = expenseRepository.findById(id).orElseThrow(() -> new BadRequestException("Expense not found!"));;
        existingExpense.setDescription(expenseDto.getDescription());
        existingExpense.setCategory(expenseDto.getCategory());
        existingExpense.setPrice(expenseDto.getPrice());
        existingExpense.setStatus(expenseDto.getStatus());
        existingExpense.setPaidBy(expenseDto.getPaidBy());
        Expenses updatedExpense = expenseRepository.save(existingExpense);
        return expenseMapper.toDto(updatedExpense);
    }

    @Override
    public String deleteExpense(Long id){
        expenseRepository.deleteById(id);
        return null;
    }

}