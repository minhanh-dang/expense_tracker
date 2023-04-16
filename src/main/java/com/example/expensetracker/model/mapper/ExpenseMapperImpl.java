package com.example.expensetracker.model.mapper;

import com.example.expensetracker.model.DTO.ExpenseDto;
import com.example.expensetracker.model.entity.Expenses;
import com.example.expensetracker.model.request.ExpenseRequest;
import com.example.expensetracker.model.response.ExpenseResponse;

public class ExpenseMapperImpl implements ExpenseMapper{

    @Override
    public Expenses toEntity(ExpenseDto expenseDto){
        Expenses expenses = new Expenses();

        expenses.setId(expenseDto.getId());
        expenses.setDescription(expenseDto.getDescription());
        expenses.setPrice(expenseDto.getPrice());
        expenses.setStatus(expenseDto.getStatus());
        expenses.setCreatedAt(expenseDto.getDateCreated());
        expenses.setModifiedAt(expenseDto.getDateModified());

        return expenses;
    }

    @Override
    public ExpenseDto toDto(Expenses expenses){
        ExpenseDto expenseDto = new ExpenseDto();

        expenseDto.setId(expenses.getId());
        expenseDto.setDescription(expenses.getDescription());
        expenseDto.setPrice(expenses.getPrice());
        expenseDto.setStatus(expenses.getStatus());
        expenseDto.setDateCreated(expenses.getCreatedAt());
        expenseDto.setDateModified(expenses.getModifiedAt());

        return expenseDto;
    }

    public ExpenseDto toDto(ExpenseRequest expenseRequest){
        ExpenseDto expenseDto = new ExpenseDto();

        expenseDto.setId(expenseRequest.getId());
        expenseDto.setDescription(expenseRequest.getDescription());
        expenseDto.setPrice(expenseRequest.getPrice());
        expenseDto.setStatus(expenseRequest.getStatus());

        return expenseDto;
    }

    public ExpenseResponse toResponse(ExpenseDto expenseDto){

        ExpenseResponse expenseResponse = new ExpenseResponse();

        expenseResponse.setId(expenseDto.getId());
        expenseResponse.setDescription(expenseDto.getDescription());
        expenseResponse.setPrice(expenseDto.getPrice());
        expenseResponse.setStatus(expenseDto.getStatus());
        expenseResponse.setCreatedAt(expenseDto.getDateCreated());
        expenseResponse.setModifiedAt(expenseDto.getDateModified());

        return expenseResponse;

    }
}
