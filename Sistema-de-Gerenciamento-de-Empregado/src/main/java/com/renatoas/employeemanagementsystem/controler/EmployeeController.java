package com.renatoas.employeemanagementsystem.controler;

import com.renatoas.employeemanagementsystem.dto.EmployeeDto;
import com.renatoas.employeemanagementsystem.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:3000") // Permite requisições de origens específicas
@RequestMapping("/api/v1/employees")
public class EmployeeController {


    private EmployeeService employeeService;


    @PostMapping
    // Define endpoints for employee management here
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        // Return the created employee with a 201 Created status
        //Verificar
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //@PathVariable é usado para extrair valores de variáveis de caminho na URL.
    @GetMapping("/{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    // Build get All Employees REST API
    @GetMapping("/getAllEmployees")
    public ResponseEntity<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    @PutMapping("{id}")                               //Define um endpoint
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                      //Recebe um json no corpo da requisição para o Objeto EmployeeDto
                                                      @RequestBody EmployeeDto updatedEmployeeDto)

    {
        EmployeeDto updatedEmployee = employeeService.updateEmployee(employeeId, updatedEmployeeDto);
        return  ResponseEntity.ok(updatedEmployee);
    }



    //Build Delete Employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId ) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }

}
