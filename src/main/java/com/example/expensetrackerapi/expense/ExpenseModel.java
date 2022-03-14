package com.example.expensetrackerapi.expense;

import com.example.expensetrackerapi.user.Employee;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Id;
import javax.persistence.*;

@Entity
@Table(name = "expenses")
@Getter
@Setter
@ToString
public class ExpenseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private Employee user;
    private String name;
    private Double value;
}
