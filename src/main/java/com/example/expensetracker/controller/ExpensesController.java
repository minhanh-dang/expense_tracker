package com.example.expensetracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.expensetracker.model.entity.Categories;
import com.example.expensetracker.model.exception.BadRequestException;
import com.example.expensetracker.repository.CategoryRepository;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetracker.model.DTO.ExpenseDto;
import com.example.expensetracker.model.mapper.ExpenseMapper;
import com.example.expensetracker.model.request.ExpenseRequest;
import com.example.expensetracker.model.response.ExpenseResponse;
import com.example.expensetracker.security.CurrentUser;
import com.example.expensetracker.security.UserPrincipal;
import com.example.expensetracker.service.ExpenseService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpensesController {

	private final ExpenseService expenseService;

	private final ExpenseMapper expenseMapper;


	@PostMapping("/addExpense")
	@PreAuthorize("hasAnyAuthority('ROLE_MANAGER','ROLE_EMPLOYEE')")
	public ExpenseResponse createExpense(@RequestBody ExpenseRequest request, @CurrentUser UserPrincipal currentUser) {

		ExpenseDto expenseDto = expenseMapper.toDto(request);
		System.out.println(expenseDto.getDescription());
		ExpenseDto savedExpenseDto = expenseService.createExpense(currentUser.getId(), expenseDto);
		return expenseMapper.toResponse(savedExpenseDto);
	}

	@GetMapping("/allExpenses")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	public List<ExpenseResponse> getAllExpenses() {
		List<ExpenseDto> expenseDto = expenseService.getAllExpenses();
		return expenseDto.stream().map(expenses -> expenseMapper.toResponse(expenses)).collect(Collectors.toList());
	}

	@GetMapping("/{expense_id}")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	public ExpenseResponse getExpenseById(@PathVariable Long expense_id) {
		ExpenseDto expenseDto = expenseService.getExpensesById(expense_id);
		return expenseMapper.toResponse(expenseDto);
	}

	@GetMapping("/my-expense")
	@PreAuthorize("hasAnyAuthority('ROLE_MANAGER','ROLE_EMPLOYEE')")
	public List<ExpenseResponse> getUserExpenses(@CurrentUser UserPrincipal currentUser) {
		List<ExpenseDto> expenseDto = expenseService.getUserExpenses(currentUser.getId());
		return expenseDto.stream().map(expense -> expenseMapper.toResponse(expense)).collect(Collectors.toList());
	}

	@PutMapping("/{user_id}/updateExpense/{expense_id}")
	@PreAuthorize("#user_id == #currentUser.id")
	public ExpenseResponse updateExpense(@CurrentUser UserPrincipal currentUser, @PathVariable Long user_id,
									  @PathVariable Long expense_id, @RequestBody ExpenseRequest expenseRequest) {

		ExpenseDto updatedExpense = expenseService.updateExpense(expense_id, expenseMapper.toDto(expenseRequest));
		return expenseMapper.toResponse(updatedExpense);

	}

	@PutMapping("/updateStatus/{expense_id}")
	@PreAuthorize("hasAuthority('ROLE_MANAGER')")
	public ExpenseResponse updateExpenseStatus(@PathVariable Long expense_id,
											   @RequestBody ExpenseRequest expenseRequest) {

		ExpenseDto updatedExpense = expenseService.updateExpenseStatus(expense_id, expenseMapper.toDto(expenseRequest));
		return expenseMapper.toResponse(updatedExpense);

	}

	@DeleteMapping("/{user_id}/deleteExpense/{expense_id}")
	@PreAuthorize("#user_id == #currentUser.id")
	public ResponseEntity<String> deleteExpense(@PathVariable Long user_id, @PathVariable Long expense_id,
												@CurrentUser UserPrincipal currentUser) {

		expenseService.deleteExpense(expense_id);
		return new ResponseEntity<>("Expense successfully deleted!", HttpStatus.OK);
	}
}