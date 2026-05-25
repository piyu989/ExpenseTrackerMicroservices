package com.expenseService.controller;

import com.expenseService.model.ExpenseDto;
import com.expenseService.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    private ExpenseService expenseService;

    @Autowired
    ExpenseController(ExpenseService expenseService){
        this.expenseService = expenseService;
    }

    @GetMapping("/expense/v1")
    public ResponseEntity<List<ExpenseDto>> getExpensesByUserId(long userId){
        try{
            List<ExpenseDto> expenses = expenseService.getExpensesByUserId(userId);
            return ResponseEntity.ok(expenses);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

}
