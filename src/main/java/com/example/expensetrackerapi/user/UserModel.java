package com.example.expensetrackerapi.user;

import com.example.expensetrackerapi.expense.ExpenseModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long Id;
    private String name;
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<ExpenseModel> expenses;

}
