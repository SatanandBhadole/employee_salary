package com.employee.salary.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.employee.salary.entities.EmployeeBonus;
import com.employee.salary.services.EmployeeBonusService;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/tci")
public class EmployeeBonusController {

    private final EmployeeBonusService employeeBonusService;

    @Autowired
    public EmployeeBonusController(EmployeeBonusService employeeBonusService) {
        this.employeeBonusService = employeeBonusService;
    }

    @PostMapping("/employee-bonus")
    public ResponseEntity<String> calculateEmployeeBonus(@RequestBody List<EmployeeBonus> employeeBonuses) {
        if (employeeBonuses == null) {
            return new ResponseEntity<String>("Request body is empty.", HttpStatus.BAD_REQUEST);
        }
        
        try {
            employeeBonusService.calculateAndSaveEmployeeBonus(employeeBonuses);
            return ResponseEntity.ok("Employee bonuses calculated and saved successfully.");
        } catch (Exception e) {
            return new ResponseEntity<String>("An error occurred while calculating and saving employee bonuses.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/employee-bonus")
    public ResponseEntity<?> getEligibleEmployees(@RequestParam String date) {
        LocalDate eligibilityDate;
        try {
            eligibilityDate = LocalDate.parse(date);
        } catch (DateTimeParseException e) {
            return new ResponseEntity<String>("Invalid date format. Please provide a valid date in the format 'yyyy-MM-dd'.", HttpStatus.BAD_REQUEST);
        }

        try {
            List<EmployeeBonus> eligibleEmployees = employeeBonusService.getEligibleEmployees(eligibilityDate);
            return ResponseEntity.ok(eligibleEmployees);
        } catch (Exception e) {
            return new ResponseEntity<String>("An error occurred while fetching eligible employees.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
