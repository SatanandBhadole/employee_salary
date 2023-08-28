package com.employee.salary.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "employee_bonus")
public class EmployeeBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "emp_name")
    private String empName;

    @Column(name = "department")
    private String department;

    @Column(name = "amount")
    private double amount;

    @Column(name = "currency")
    private String currency;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "exit_date")
    private LocalDate exitDate;

    // Getters and setters
}
