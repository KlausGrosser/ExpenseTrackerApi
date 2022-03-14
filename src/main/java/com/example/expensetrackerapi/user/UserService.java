package com.example.expensetrackerapi.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    public UserModel addUser(UserModel newUser) {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username(newUser.getName())
                .password(newUser.getPassword())
                .roles("ADMIN")
                .build();
        authenticateUser(user);
        return userRepository.save(newUser);
    }

    public InMemoryUserDetailsManager authenticateUser(UserDetails user) {
        return new InMemoryUserDetailsManager(user);
    }
}
