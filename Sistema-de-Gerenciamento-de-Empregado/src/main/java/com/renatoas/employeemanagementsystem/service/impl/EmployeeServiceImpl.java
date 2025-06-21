package com.renatoas.employeemanagementsystem.service.impl;

// Implementação da camada de serviço para Employee.
// como criar, buscar, atualizar e deletar registros.

import com.renatoas.employeemanagementsystem.dto.EmployeeDto;
import com.renatoas.employeemanagementsystem.entity.Employee;
import com.renatoas.employeemanagementsystem.exception.ResourceNotFoundException;
import com.renatoas.employeemanagementsystem.mapper.EmployeeMapper;
import com.renatoas.employeemanagementsystem.repository.EmployeeRepository;
import com.renatoas.employeemanagementsystem.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;

// Esta classe é anotada com @Service para que o Spring a reconheça como um bean de serviço.
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
   private EmployeeRepository employeeRepository;


    // Implementação do método createEmployee da interface EmployeeService.
    // Este método recebe um EmployeeDto, converte para um Employee, salva no repositório e retorna o EmployeeDto salvo.
    //
    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
    }

    // Implementação do método getAllEmployees da interface EmployeeService.
    // Sobrecereve o metodo definnido na interface EmployeeService. ao qual está com os componets do JPA sendo ijetados
    // Permitindo a busca de todos os funcionários no repositório e retornando uma lista de EmployeeDto
    // @Override Indica que este método está sobrescrevendo um método da interface EmployeeService.
    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() -> new ResourceNotFoundException("Funcionário não encontrado com id: " + employeeId));
        // Utiliza o EmployeeMapper para converter o Employee em EmployeeDto
        // O metodo que assina getEmployeeById na interface EmployeeService
        // retorna um EmployeeDto, que é uma representação do funcionário.
        // O EmployeeMapper é uma classe utilitária que converte entre entidades e DTOs.
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