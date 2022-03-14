package com.example.expensetrackerapi.user;

import com.example.expensetrackerapi.expense.ExpenseModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.Id;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long Id;
    @Column(unique = true)
    private String name;
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private List<ExpenseModel> expenses;

    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
