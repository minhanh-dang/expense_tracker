package com.example.expensetracker;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import com.example.expensetracker.model.DTO.ExpenseDto;
import com.example.expensetracker.model.entity.*;
import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.model.mapper.ExpenseMapper;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import com.example.expensetracker.repository.UserRepository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.example.expensetracker.service.ExpenseServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ExpenseServiceImplTest {

    @Mock
    private ExpenseRepository expenseRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ExpenseMapper expenseMapper;

    @InjectMocks
    private ExpenseServiceImpl expenseService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateExpense() {
        // Arrange
        Long userId = 1L;
        Users user = new Users();
        user.setId(userId);

        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setCategory("TEST");
        expenseDto.setDescription("Test expense");
        expenseDto.setPrice(BigDecimal.valueOf(100));
        expenseDto.setStatus(ExpenseStatus.TO_SUBMIT);
        expenseDto.setPaidBy(PaidBy.EMPLOYEE);

        Categories category = new Categories();
        category.setId("TEST");
        Expenses expense = new Expenses();
        expense.setUser(user);
        expense.setDescription(expenseDto.getDescription());
        expense.setCategory(category);
        expense.setPrice(expenseDto.getPrice());
        expense.setStatus(expenseDto.getStatus());
        expense.setPaidBy(expenseDto.getPaidBy());
        expense.setId(1L);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(categoryRepository.findById(expenseDto.getCategory())).thenReturn(Optional.of(category));
        when(expenseRepository.save(expense)).thenReturn(expense);
        when(expenseMapper.toDto(expense)).thenReturn(expenseDto);

        // Act
        ExpenseDto createdExpenseDto = expenseService.createExpense(userId, expenseDto);

        // Assert
        // ERROR
        assertEquals(expenseDto, createdExpenseDto);
    }

    @Test
    public void testGetAllExpenses() {
        // Arrange
        ExpenseDto expenseDto1 = new ExpenseDto();
        expenseDto1.setId(1L);
        ExpenseDto expenseDto2 = new ExpenseDto();
        expenseDto2.setId(2L);
        List<ExpenseDto> expenseDtos = Arrays.asList(expenseDto1, expenseDto2);
        when(expenseRepository.findAll()).thenReturn(Arrays.asList(new Expenses(), new Expenses()));
        when(expenseMapper.toDto(any(Expenses.class))).thenReturn(expenseDto1, expenseDto2);

        // Act
        List<ExpenseDto> result = expenseService.getAllExpenses();

        // Assert
        Assertions.assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expenseDtos, result);
    }

    @Test
    public void testGetUserExpenses() {
        // Arrange
        Long userId = 1L;
        ExpenseDto expenseDto1 = new ExpenseDto();
        expenseDto1.setId(1L);
        ExpenseDto expenseDto2 = new ExpenseDto();
        expenseDto2.setId(2L);
        List<ExpenseDto> expenseDtos = Arrays.asList(expenseDto1, expenseDto2);
        when(expenseRepository.findByUserId(anyLong())).thenReturn(Optional.of(Arrays.asList(new Expenses(), new Expenses())));
        when(expenseMapper.toDto(any(Expenses.class))).thenReturn(expenseDto1, expenseDto2);

        // Act
        List<ExpenseDto> result = expenseService.getUserExpenses(userId);

        // Assert
        Assertions.assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expenseDtos, result);
    }

    @Test
    public void testGetExpensesById() {
        // Arrange
        Long expenseId = 1L;
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setId(expenseId);
        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(new Expenses()));
        when(expenseMapper.toDto(any(Expenses.class))).thenReturn(expenseDto);

        // Act
        ExpenseDto result = expenseService.getExpensesById(expenseId);

        // Assert
        Assertions.assertNotNull(result);
        assertEquals(expenseDto, result);
    }

    @Test
    public void testUpdateExpense() {
        // Arrange

        Long expenseId = 1L;
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setCategory("TEST");
        expenseDto.setDescription("Test expense");
        expenseDto.setPrice(BigDecimal.valueOf(100));
        expenseDto.setStatus(ExpenseStatus.TO_SUBMIT);
        expenseDto.setPaidBy(PaidBy.EMPLOYEE);

        Categories category = new Categories();
        category.setId(expenseDto.getCategory());
        Expenses existingExpense = new Expenses();
        existingExpense.setId(expenseId);
        existingExpense.setCategory(new Categories());

        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(existingExpense));
        when(categoryRepository.findById(anyString())).thenReturn(Optional.of(category));
        when(expenseMapper.toDto(any(Expenses.class))).thenReturn(expenseDto);
        when(expenseRepository.save(any(Expenses.class))).thenReturn(existingExpense);

        // Act
        ExpenseDto result = expenseService.updateExpense(expenseId, expenseDto);

        // Assert
        Assertions.assertNotNull(result);
        assertEquals(expenseDto, result);
    }

    @Test
    public void testUpdateExpenseStatus() {
        // Arrange
        Long expenseId = 1L;
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setId(expenseId);
        expenseDto.setStatus(ExpenseStatus.APPROVED);

        Expenses existingExpense = new Expenses();
        existingExpense.setId(expenseId);
        existingExpense.setStatus(ExpenseStatus.SUBMITTED);
        when(expenseRepository.findById(anyLong())).thenReturn(Optional.of(existingExpense));
        when(expenseMapper.toDto(any(Expenses.class))).thenReturn(expenseDto);
        when(expenseRepository.save(any(Expenses.class))).thenReturn(existingExpense);

        // Act
        ExpenseDto result = expenseService.updateExpenseStatus(expenseId, expenseDto);

        // Assert
        Assertions.assertNotNull(result);
        assertEquals(expenseDto, result);
    }

    @Test
    public void testDeleteExpense() {
        // Arrange
        Long expenseId = 1L;

        // Act
        String result = expenseService.deleteExpense(expenseId);

        // Assert
        Assertions.assertNull(result);
    }

    @Test
    public void testCreateExpenseWithInvalidUserId() {
        // Arrange
        Long userId = 1L;

        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setCategory("TEST");
        expenseDto.setDescription("Test expense");
        expenseDto.setPrice(BigDecimal.valueOf(100));
        expenseDto.setStatus(ExpenseStatus.TO_SUBMIT);
        expenseDto.setPaidBy(PaidBy.EMPLOYEE);
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(BadRequestException.class, () -> expenseService.createExpense(userId, expenseDto));
    }

    @Test
    public void testCreateExpenseWithInvalidCategoryId() {
        // Arrange
        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setCategory("TEST");
        expenseDto.setDescription("Test expense");
        expenseDto.setPrice(BigDecimal.valueOf(100));
        expenseDto.setStatus(ExpenseStatus.TO_SUBMIT);
        expenseDto.setPaidBy(PaidBy.EMPLOYEE);

        Long userId = 1L;
        Users user = new Users();
        user.setId(userId);
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
        when(categoryRepository.findById(anyString())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(BadRequestException.class, () -> expenseService.createExpense(userId, expenseDto));
    }

    @Test
    public void testGetUserExpensesWithInvalidUserId() {
        // Arrange
        Long userId = 1L;
        when(expenseRepository.findByUserId(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(BadRequestException.class, () -> expenseService.getUserExpenses(userId));
    }

    @Test
    public void testGetExpensesByIdWithInvalidExpenseId() {
        // Arrange
        Long expenseId = 1L;
        when(expenseRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(BadRequestException.class, () -> expenseService.getExpensesById(expenseId));
    }

    @Test
    public void testUpdateExpenseWithInvalidExpenseId() {
        // Arrange
        Long expenseId = 1L;

        ExpenseDto expenseDto = new ExpenseDto();
        expenseDto.setCategory("TEST");
        expenseDto.setDescription("Test expense");
        expenseDto.setPrice(BigDecimal.valueOf(100));
        expenseDto.setStatus(ExpenseStatus.TO_SUBMIT);
        expenseDto.setPaidBy(PaidBy.EMPLOYEE);

        when(expenseRepository.findById(expenseId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(BadRequestException.class, () -> expenseService.updateExpense(expenseId, expenseDto));
    }
}