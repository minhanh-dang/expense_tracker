package com.example.expensetracker.model.response;

import com.example.expensetracker.model.entity.Categories;
import com.example.expensetracker.model.entity.ExpenseStatus;
import com.example.expensetracker.model.entity.PaidBy;
import com.example.expensetracker.model.entity.Users;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Currency;

@Getter
@Setter
public class ExpenseResponse {

    private Long id;

    private String description;

    private Categories category;

    private BigDecimal price;

    private ExpenseStatus status;

    private PaidBy paidBy;

    private Instant createdAt;

    private Instant modifiedAt;

}
