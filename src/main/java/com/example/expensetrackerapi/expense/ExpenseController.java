package com.example.expensetrackerapi.expense;

import com.example.expensetrackerapi.user.Employee;
import com.example.expensetrackerapi.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private EmployeeRepository userRepository;

    @GetMapping
    public List<ExpenseModel> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @GetMapping("/{id}")
    public ExpenseModel getExpenseById(@PathVariable (value ="id") long id) {
        return expenseRepository.findById(id).orElseThrow();
    }

    @GetMapping("/{name}")
    public ExpenseModel getExpenseByName(@PathVariable (value ="name") String name) {
        return expenseRepository.findByName(name).orElseThrow();
    }

    @PostMapping("/add/{userId}")
    public ExpenseModel addExpenseToUser(@PathVariable (value ="userId") Long userId,
                                         @RequestBody ExpenseModel newExpense){

        Employee user = userRepository.findById(userId).orElseThrow();
        newExpense.setUser(user);
        user.getExpenses().add(newExpense);
        return expenseRepository.save(newExpense);
    }
}
