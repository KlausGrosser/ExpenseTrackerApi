package com.example.expensetrackerapi.user;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService implements UserDetailsService {

    private EmployeeRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Employee addUser(Employee newUser) {
        newUser.setPassword(
                bCryptPasswordEncoder
                        .encode(newUser.getPassword()));
        Employee emp = userRepository.save(newUser);

//        UserDetails x = loadUserByUsername(emp.getName());
//        Authentication auth = new UsernamePasswordAuthenticationToken(
//                x,
//                null,
//                getAuthorities());
//        SecurityContextHolder.getContext().setAuthentication(auth);
        return emp;
    }


    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
            Employee employee = userRepository.findByName(name).orElseThrow();
            if (employee == null) {
                throw new UsernameNotFoundException("No user found with name: " + name);
            }
            boolean enabled = true;
            boolean accountNonExpired = true;
            boolean credentialsNonExpired = true;
            boolean accountNonLocked = true;


            return new User(employee.getName(), employee.getPassword(), enabled, accountNonExpired,
                    credentialsNonExpired, accountNonLocked, getAuthorities());
        }

        private static List<GrantedAuthority> getAuthorities () {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("USER"));
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            return authorities;
        }
    }

