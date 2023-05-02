package com.example.expensetracker.model.DTO;

import com.example.expensetracker.model.entity.Categories;
import com.example.expensetracker.model.entity.ExpenseStatus;
import com.example.expensetracker.model.entity.PaidBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDto {
    private Long id;
    private String description;
    private String category;
    private ExpenseStatus status;
    private BigDecimal price;
    private PaidBy paidBy;
    private Instant dateCreated;
    private Instant dateModified;
    private UserDto userDto;
}
