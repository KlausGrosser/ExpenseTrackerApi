package com.example.expensetrackerapi.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository userRepository;

    private EmployeeModelDetailsService eMDS;


    public Employee addUser(Employee newUser) {
        Employee emp = userRepository.save(newUser);

        UserDetails x = eMDS.loadUserByUsername(emp.getName());
        Authentication auth = new UsernamePasswordAuthenticationToken(
                x,
                null,
                EmployeeModelDetailsService.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username(newUser.getName())
//                .password(newUser.getPassword())
//                .roles("ADMIN")
//                .build();
//        authenticateUser(user);
        return emp;
    }

    public InMemoryUserDetailsManager authenticateUser(UserDetails user) {
        return new InMemoryUserDetailsManager(user);
    }
}
