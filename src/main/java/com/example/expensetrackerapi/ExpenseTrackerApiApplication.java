package com.example.expensetrackerapi;

import com.example.expensetrackerapi.user.Employee;
import com.example.expensetrackerapi.user.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ExpenseTrackerApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpenseTrackerApiApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner loadData(
            EmployeeRepository employeeRepository) {
        return (args) -> {
            employeeRepository.save(
                    new Employee("user", bCryptPasswordEncoder().encode("password")));
        };
    }


}
