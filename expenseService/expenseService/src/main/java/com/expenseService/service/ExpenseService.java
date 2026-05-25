package com.expenseService.service;

import com.expenseService.entity.Expense;
import com.expenseService.model.ExpenseDto;
import com.expenseService.repository.ExpenseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private ObjectMapper objectMapper;

    @Autowired
    ExpenseService(ExpenseRepository expenseRepository,ObjectMapper objectMapper){
        this.expenseRepository = expenseRepository;
        this.objectMapper = objectMapper;
    }

    public boolean createExpense(ExpenseDto dto){
        setCurrency(dto);
        try{
            expenseRepository.save(objectMapper.convertValue(dto, Expense.class));
            return true;
        }catch (Exception exception){
            exception.printStackTrace();
        }
        return false;
    }

    public boolean updateExpense(ExpenseDto dto){
        Optional<Expense> byUserIdAndExternalId = expenseRepository.findByUserIdAndExternalId(dto.getUserId(), dto.getExternalId());
        if(!byUserIdAndExternalId.isPresent()){
            return false;
        }
        Expense expense = byUserIdAndExternalId.get();
        expense.setAmount(dto.getAmount());
        expense.setCurrency(dto.getCurrency());
        expense.setMerchant(dto.getMerchant());

        expenseRepository.save(expense);

        return true;
    }

    public List<ExpenseDto> getExpensesByUserId(long userId) {
        List<Expense> expenses = expenseRepository.findByUserId(userId);
        return objectMapper.convertValue(expenses, new TypeReference<List<ExpenseDto>>() {});
    }

    private void setCurrency(ExpenseDto dto) {
        if(dto.getCurrency() != null){
            dto.setCurrency("INR");
            dto.setCreatedAt(Timestamp.from(Instant.now()));
        }
    }



}
