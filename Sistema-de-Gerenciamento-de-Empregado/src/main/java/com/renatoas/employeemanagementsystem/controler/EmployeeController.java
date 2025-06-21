package com.renatoas.employeemanagementsystem.controler;

import com.renatoas.employeemanagementsystem.dto.EmployeeDto;
import com.renatoas.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //@PathVariable é usado para extrair valores de variáveis de caminho na URL.
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }


}
