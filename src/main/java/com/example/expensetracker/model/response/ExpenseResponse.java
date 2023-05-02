package com.example.expensetracker.model.response;

import com.example.expensetracker.model.entity.Categories;
import com.example.expensetracker.model.entity.ExpenseStatus;
import com.example.expensetracker.model.entity.PaidBy;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;


@Getter
@Setter
public class ExpenseResponse {

    private Long id;

    private String description;

    private String category;

    private BigDecimal price;

    private ExpenseStatus status;

    private PaidBy paidBy;

    private Instant createdAt;

    private Instant modifiedAt;

}
