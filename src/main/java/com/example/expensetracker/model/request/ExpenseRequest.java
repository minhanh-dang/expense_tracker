package com.example.expensetracker.model.request;

import com.example.expensetracker.model.entity.Categories;
import com.example.expensetracker.model.entity.ExpenseStatus;
import com.example.expensetracker.model.entity.PaidBy;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Currency;

@Getter
@Setter
public class ExpenseRequest {

    private Long id;

    private String description;

    private Categories category;

    private BigDecimal price;

    private ExpenseStatus status;

    private PaidBy paidBy;

}
