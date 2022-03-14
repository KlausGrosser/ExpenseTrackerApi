package com.example.expensetrackerapi.expense;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {

    Optional<ExpenseModel> findByName(String name);
}
