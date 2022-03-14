package com.example.expensetrackerapi.expense;

import com.example.expensetrackerapi.user.Employee;
import com.example.expensetrackerapi.user.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpenseService {

    private ExpenseRepository expenseRepository;
    private EmployeeRepository employeeRepository;

    public List<ExpenseModel> getAllExpenses(){
        return expenseRepository.findAll();
    }

    public ExpenseModel getExpenseById(long id) {
        return expenseRepository.findById(id).orElseThrow();
    }

    public ExpenseModel getExpenseByName(String name){
        return expenseRepository.findByName(name).orElseThrow();
    }

    public ExpenseModel addExpense(ExpenseModel newExpense, long userId){
        Employee user = employeeRepository.findById(userId).orElseThrow();
        newExpense.setUser(user);
        user.getExpenses().add(newExpense);
        return expenseRepository.save(newExpense);
    }

    public List<ExpenseModel> getAllUserExpenses(long userId){
        Employee employee = employeeRepository.findById(userId).orElseThrow();
        return employee.getExpenses();
    }

    public void deleteExpense(long expenseId) {
        expenseRepository.deleteById(expenseId);
    }


}
