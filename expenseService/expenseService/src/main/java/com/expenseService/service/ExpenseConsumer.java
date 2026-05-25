package com.expenseService.service;

import com.expenseService.model.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ExpenseConsumer {

    private ExpenseService expenseService;
    private ObjectMapper objectMapper;

    @Autowired
    ExpenseConsumer(ExpenseService expenseService,ObjectMapper objectMapper){
        this.expenseService = expenseService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ExpenseDto dto) {
        try{
            expenseService.createExpense(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
