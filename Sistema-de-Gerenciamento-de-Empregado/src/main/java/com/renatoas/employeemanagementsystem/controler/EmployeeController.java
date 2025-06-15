package com.renatoas.employeemanagementsystem.controler;

import com.renatoas.employeemanagementsystem.dto.EmployeeDto;
import com.renatoas.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/employees")
@AllArgsConstructor
public class EmployeeController {


    private EmployeeService employeeService;


    @PostMapping
    // Define endpoints for employee management here
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        // Return the created employee with a 201 Created status
        //Verificar
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

}
