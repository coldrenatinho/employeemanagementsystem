package com.renatoas.employeemanagementsystem.service.impl;

// Implementação da camada de serviço para Employee.
// Esta classe geralmente contém a lógica de negócio relacionada a funcionários,
// como criar, buscar, atualizar e deletar registros.
// Observação: Normalmente, ela implementa a interface EmployeeService e utiliza o repositório para acessar o banco de dados.

import com.renatoas.employeemanagementsystem.dto.EmployeeDto;
import com.renatoas.employeemanagementsystem.entity.Employee;
import com.renatoas.employeemanagementsystem.mapper.EmployeeMapper;
import com.renatoas.employeemanagementsystem.repository.EmployeeRepository;
import com.renatoas.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

// Esta classe é anotada com @Service para que o Spring a reconheça como um bean de serviço.
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
   private EmployeeRepository employeeRepository;


    // Implementação do método createEmployee da interface EmployeeService.
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {

        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new RuntimeException("Funcionário não encontrado com id: " + employeeId));
        return EmployeeMapper.mapToEmployeeDto(employee);
    }

    // Métodos de negócio seriam implementados aqui, por exemplo:
    // - createEmployee
    // - getEmployeeById
    // - getAllEmployees
    // - updateEmployee
    // - deleteEmployee

    // Observação: Lembre-se de anotar com @Service para que o Spring gerencie esta classe como um bean de serviço.
}