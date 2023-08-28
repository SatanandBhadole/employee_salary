package com.employee.salary.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.salary.entities.EmployeeBonus;
import com.employee.salary.repositories.EmployeeBonusRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmployeeBonusService {

    private final EmployeeBonusRepository employeeBonusRepository;

    @Autowired
    public EmployeeBonusService(EmployeeBonusRepository employeeBonusRepository) {
        this.employeeBonusRepository = employeeBonusRepository;
    }

    public void calculateAndSaveEmployeeBonus(List<EmployeeBonus> employeeBonuses) {
        employeeBonusRepository.saveAll(employeeBonuses);
    }

    public List<EmployeeBonus> getEligibleEmployees(LocalDate date) {
    	return employeeBonusRepository.findAll();
    }
}

