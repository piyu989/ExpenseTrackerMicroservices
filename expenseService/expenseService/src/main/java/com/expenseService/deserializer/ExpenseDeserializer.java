package com.expenseService.deserializer;

import com.expenseService.model.ExpenseDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ExpenseDeserializer implements Deserializer<ExpenseDto> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);
    }

    @Override
    public ExpenseDto deserialize(String var1, byte[] var2){
        System.out.println("deserilazing info "+var1);
        ObjectMapper objectMapper = new ObjectMapper();
        ExpenseDto expense = null;
        try{
            expense = objectMapper.readValue(var2, ExpenseDto.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return expense;
    }

    public void close() {}
}
