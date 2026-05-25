package com.expenseService.repository;

import com.expenseService.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    List<Expense> findByUserId(long userId);
    List<Expense> findByUserIdAndCreatedAtBetween(long userId, String startDate, String endDate);
    Optional<Expense> findByUserIdAndExternalId(long uid, String externalId);
}
