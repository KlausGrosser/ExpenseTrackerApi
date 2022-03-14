package com.example.expensetrackerapi.user;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService userService;

    private EmployeeRepository userRepository;

    @GetMapping
    public List<Employee> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getUser(@PathVariable(value ="id") long id){
        return userRepository.findById(id)
                .orElseThrow();
    }

    @PostMapping("/add")
    public Employee addUser(@Validated @RequestBody Employee newUser){
        return userService.addUser(newUser);
    }

    @PutMapping("/update/{id}")
    public Employee updateUser(@PathVariable(value ="id") long id, @Validated @RequestBody Employee newUserDetails){
        Employee userToUpdate = userRepository.findById(id)
                .orElseThrow();
        if(newUserDetails.getId() != 0){
            userToUpdate.setId(newUserDetails.getId());
        }

        if(!newUserDetails.getName().isBlank()){
            userToUpdate.setName(newUserDetails.getName());
        }

        return userRepository.save(userToUpdate);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable(value ="id") long id){
        userRepository.deleteById(id);
    }

}
