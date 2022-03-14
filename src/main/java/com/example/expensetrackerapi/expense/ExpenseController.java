package com.example.expensetrackerapi.expense;

import com.example.expensetrackerapi.user.UserModel;
import com.example.expensetrackerapi.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserRepository userRepository;

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

        UserModel user = userRepository.findById(userId).orElseThrow();
        newExpense.setUser(user);
        user.getExpenses().add(newExpense);
        return expenseRepository.save(newExpense);
    }
}
