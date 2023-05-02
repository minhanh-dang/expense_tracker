package com.example.expensetracker.model.entity;

import javax.persistence.*;

public enum ExpenseStatus {
    TO_SUBMIT, SUBMITTED, APPROVED, PAID, REFUSED;
}
