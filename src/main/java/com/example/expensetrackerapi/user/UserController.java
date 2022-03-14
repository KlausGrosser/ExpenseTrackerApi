package com.example.expensetrackerapi.user;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    private UserRepository userRepository;

    @GetMapping
    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public UserModel getUser(@PathVariable(value ="id") long id){
        return userRepository.findById(id)
                .orElseThrow();
    }

    @PostMapping("/add")
    public UserModel addUser(@Validated @RequestBody UserModel newUser){
        return userService.addUser(newUser);
    }

    @PutMapping("/update/{id}")
    public UserModel updateUser(@PathVariable(value ="id") long id, @Validated @RequestBody UserModel newUserDetails){
        UserModel userToUpdate = userRepository.findById(id)
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
