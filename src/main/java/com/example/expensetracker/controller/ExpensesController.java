package com.example.expensetracker.controller;

import com.example.expensetracker.model.DTO.ExpenseDto;
import com.example.expensetracker.model.mapper.ExpenseMapper;
import com.example.expensetracker.model.mapper.UserMapper;
import com.example.expensetracker.model.request.ExpenseRequest;
import com.example.expensetracker.model.response.ExpenseResponse;
import com.example.expensetracker.security.CurrentUser;
import com.example.expensetracker.security.UserPrincipal;
import com.example.expensetracker.service.ExpenseService;
import com.example.expensetracker.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpensesController {

    private final UserService userService;

    private final ExpenseService expenseService;

    private final UserMapper userMapper;

    private final ExpenseMapper expenseMapper;

    @PostMapping("/addExpense")
    @PreAuthorize("hasAnyAuthority('ROLE_MANAGER','ROLE_EMPLOYEE')")
    public ExpenseResponse createExpense(@RequestBody ExpenseRequest request, @CurrentUser UserPrincipal currentUser){
        ExpenseDto expenseDto = expenseMapper.toDto(request);
        System.out.println(expenseDto.getDescription());
        ExpenseDto savedExpenseDto = expenseService.createExpense(currentUser.getId(), expenseDto);
        return expenseMapper.toResponse(savedExpenseDto);
    }

    @GetMapping("/todos")
    @PreAuthorize("hasAuthority('ROLE_MANAGER')")
    public List<ExpenseResponse> getAllExpenses() {
        List<ExpenseDto> expenseDto = expenseService.getAllExpenses();
        return expenseDto.stream().map(expenses -> expenseMapper.toResponse(expenses)).collect(Collectors.toList());
    }

    @GetMapping("/my-todos")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN','ROLE_USER')")
    public List<ExpenseResponse> getUserToDo(@CurrentUser UserPrincipal currentUser) {
        List<ExpenseDto> expenseDto = expenseService.getUserExpenses(currentUser.getId());
        return expenseDto.stream().map(expense -> expenseMapper.toResponse(expense)).collect(Collectors.toList());
    }

    @PutMapping("{user_id}/updateToDo/{todo_id}")
    @PreAuthorize("#user_id == #currentUser.id")
    public ExpenseResponse updateToDo(@CurrentUser UserPrincipal currentUser, @PathVariable Long user_id, @PathVariable Long expense_id,
                                   @RequestBody ExpenseRequest expenseRequest){

        ExpenseDto updatedExpense = expenseService.updateExpense(expense_id, expenseMapper.toDto(expenseRequest));
        return expenseMapper.toResponse(updatedExpense);

    }

    @DeleteMapping("{user_id}/deleteToDo/{todo_id}")
    @PreAuthorize("#user_id == #currentUser.id")
    public ResponseEntity<String> deleteExpense(@PathVariable Long user_id, @PathVariable Long expense_id,
                                             @CurrentUser UserPrincipal currentUser){

        expenseService.deleteExpense(expense_id);
        return new ResponseEntity<>("ToDo successfully deleted!", HttpStatus.OK);
    }
}
