package com.employee.salary.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.salary.entities.EmployeeBonus;

@Repository
public interface EmployeeBonusRepository extends JpaRepository<EmployeeBonus, Long> {
}
