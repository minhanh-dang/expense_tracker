package com.example.expensetracker.model.mapper;

import com.example.expensetracker.model.DTO.ExpenseDto;
import com.example.expensetracker.model.entity.Expenses;
import com.example.expensetracker.model.request.ExpenseRequest;
import com.example.expensetracker.model.response.ExpenseResponse;

public interface ExpenseMapper {

    Expenses toEntity(ExpenseDto expenseDto);

    public ExpenseDto toDto(Expenses expenses);

    public ExpenseDto toDto(ExpenseRequest expenseRequest);

    public ExpenseResponse toResponse(ExpenseDto expenseDto);

}
