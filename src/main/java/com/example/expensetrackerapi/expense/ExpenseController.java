package com.example.expensetrackerapi.expense;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    public List<ExpenseModel> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/{id}")
    public ExpenseModel getExpenseById(@PathVariable (value ="id") long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/{name}")
    public ExpenseModel getExpenseByName(@PathVariable (value ="name") String name) {
        return expenseService.getExpenseByName(name);
    }

    @GetMapping("/{userId}")
    public List<ExpenseModel> getExpensesByUserId(@PathVariable (value ="userId") long userId) {
        return expenseService.getAllUserExpenses(userId);
    }

    @PostMapping("/add/{userId}")
    public ExpenseModel addExpenseToUser(@PathVariable (value ="userId") long userId,
                                         @RequestBody ExpenseModel newExpense){
        return expenseService.addExpense(newExpense, userId);
    }

    @DeleteMapping("/delete/{expenseId}")
    public void deleteUserExpense(@PathVariable(value ="expenseId") long expenseId){
        expenseService.deleteExpense(expenseId);
    }
}
