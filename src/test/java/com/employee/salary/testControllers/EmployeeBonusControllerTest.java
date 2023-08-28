package com.employee.salary.testControllers;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.Assertions; // Import JUnit assertions

import com.employee.salary.controllers.EmployeeBonusController;
import com.employee.salary.entities.EmployeeBonus;
import com.employee.salary.services.EmployeeBonusService;

public class EmployeeBonusControllerTest {

    private EmployeeBonusController employeeBonusController;
    private EmployeeBonusService employeeBonusService;

    @BeforeEach
    public void setUp() {
        employeeBonusService = mock(EmployeeBonusService.class);
        employeeBonusController = new EmployeeBonusController(employeeBonusService);
    }

    @Test
    public void testCalculateEmployeeBonus_Success() {
        List<EmployeeBonus> employeeBonuses = new ArrayList<EmployeeBonus>();
        employeeBonuses.add(new EmployeeBonus());
        
        ResponseEntity<String> response = employeeBonusController.calculateEmployeeBonus(employeeBonuses);

        verify(employeeBonusService).calculateAndSaveEmployeeBonus(employeeBonuses);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("Employee bonuses calculated and saved successfully.", response.getBody());
    }

    @Test
    public void testCalculateEmployeeBonus_NullInput() {
        ResponseEntity<String> response = employeeBonusController.calculateEmployeeBonus(null);

        Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        Assertions.assertNull(response.getBody());
    }

    @Test
    public void testGetEligibleEmployees_Success() {
        LocalDate eligibilityDate = LocalDate.now();
        List<EmployeeBonus> eligibleEmployees = new ArrayList<EmployeeBonus>();
        when(employeeBonusService.getEligibleEmployees(eligibilityDate)).thenReturn(eligibleEmployees);

        ResponseEntity<?> response = employeeBonusController.getEligibleEmployees(eligibilityDate.toString());

        verify(employeeBonusService).getEligibleEmployees(eligibilityDate);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(eligibleEmployees, response.getBody());
    }
}
