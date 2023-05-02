package com.example.expensetracker.model.mapper;

import com.example.expensetracker.model.DTO.ExpenseDto;
import com.example.expensetracker.model.entity.Categories;
import com.example.expensetracker.model.entity.ExpenseStatus;
import com.example.expensetracker.model.entity.Expenses;
import com.example.expensetracker.model.request.ExpenseRequest;
import com.example.expensetracker.model.response.ExpenseResponse;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Component;


@Component
public class ExpenseMapperImpl implements ExpenseMapper{


    @Override
    public Expenses toEntity(ExpenseDto expenseDto){
        Expenses expenses = new Expenses();

        expenses.setId(expenseDto.getId());
        expenses.setDescription(expenseDto.getDescription());
        expenses.setPrice(expenseDto.getPrice());
        expenses.setStatus(expenseDto.getStatus());
        expenses.setPaidBy(expenseDto.getPaidBy());
        expenses.setCreatedAt(expenseDto.getDateCreated());
        expenses.setModifiedAt(expenseDto.getDateModified());

        return expenses;
    }

    @Override
    public ExpenseDto toDto(Expenses expenses){
        ExpenseDto expenseDto = new ExpenseDto();

        expenseDto.setId(expenses.getId());
        expenseDto.setDescription(expenses.getDescription());
        expenseDto.setCategory(expenses.getCategory().getId());
        expenseDto.setPrice(expenses.getPrice());
        expenseDto.setStatus(expenses.getStatus());
        expenseDto.setPaidBy(expenses.getPaidBy());
        expenseDto.setDateCreated(expenses.getCreatedAt());
        expenseDto.setDateModified(expenses.getModifiedAt());

        return expenseDto;
    }

    public ExpenseDto toDto(ExpenseRequest expenseRequest){
        ExpenseDto expenseDto = new ExpenseDto();

        expenseDto.setDescription(expenseRequest.getDescription());
        expenseDto.setPrice(expenseRequest.getPrice());
        expenseDto.setStatus(expenseRequest.getStatus());
        expenseDto.setCategory(expenseRequest.getCategory());
        expenseDto.setPaidBy(expenseRequest.getPaidBy());

        return expenseDto;
    }

//    public ExpenseDto toDto(ExpenseStatus expenseStatus){
//        ExpenseDto expenseDto = new ExpenseDto();
//
//        expenseDto.setStatus(expenseStatus);
//
//        return expenseDto;
//    }

    public ExpenseResponse toResponse(ExpenseDto expenseDto){

        ExpenseResponse expenseResponse = new ExpenseResponse();

        expenseResponse.setId(expenseDto.getId());
        expenseResponse.setDescription(expenseDto.getDescription());
        expenseResponse.setPrice(expenseDto.getPrice());
        expenseResponse.setStatus(expenseDto.getStatus());
        expenseResponse.setCategory(expenseDto.getCategory());
        expenseResponse.setPaidBy(expenseDto.getPaidBy());
        expenseResponse.setCreatedAt(expenseDto.getDateCreated());
        expenseResponse.setModifiedAt(expenseDto.getDateModified());

        return expenseResponse;

    }
}
