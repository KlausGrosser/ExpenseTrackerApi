package com.example.expensetrackerapi.user;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService implements UserDetailsService {

    private EmployeeRepository employeeRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(long id) {
        return employeeRepository.findById(id)
                .orElseThrow();
    }

    public Employee addUser(Employee newUser) {
        newUser.setPassword(
                bCryptPasswordEncoder
                        .encode(newUser.getPassword()));

        return employeeRepository.save(newUser);
    }

    public Employee updateUser(Employee newUserDetails, long id) {
        Employee userToUpdate = employeeRepository.findById(id)
                .orElseThrow();
        if(newUserDetails.getId() != 0){
            userToUpdate.setId(newUserDetails.getId());
        }

        if(!newUserDetails.getName().isBlank()){
            userToUpdate.setName(newUserDetails.getName());
        }

        return employeeRepository.save(userToUpdate);
    }

    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
            Employee employee = employeeRepository.findByName(name).orElseThrow();
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

        public void deleteUserById(long id) {
            employeeRepository.deleteById(id);
        }

    }

