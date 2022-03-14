package com.example.expensetrackerapi.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllUsers() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getUser(@PathVariable(value ="id") long id){
       return employeeService.getEmployeeById(id);
    }

    @PostMapping("/add")
    public Employee addUser(@Validated @RequestBody Employee newUser){
        return employeeService.addUser(newUser);
    }

    @PutMapping("/update/{id}")
    public Employee updateUser(@PathVariable(value ="id") long id, @Validated @RequestBody Employee newUserDetails){
        return employeeService.updateUser(newUserDetails, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value ="id") long id){
        employeeService.deleteUserById(id);
    }

}
