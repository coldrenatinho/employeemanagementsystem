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
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<EmployeeDto> getAllEmployees() {
        // Busca todos os funcionários do repositório e converte para uma lista de EmployeeDto
        // O método findAll() do repositório retorna uma lista de Employee, que é convertida para EmployeeDto usando o EmployeeMapper.
        // O método retorna uma lista de EmployeeDto, que é uma representação dos funcionários.
       List<Employee> employees = employeeRepository.findAll();
        return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toUnmodifiableList());
    }

    //Recebe o ID do funcionário a ser atualizado e um EmployeeDto com os novos dados.
    // Um Objeto DTO (Data Transfer Object) é usado para transferir dados entre as camadas da aplicação.
    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updatedEmployeeDto) {

        // Instancia um objeto Employee usando o EmployeeMapper, que converte o EmployeeDto em um Employee
        Employee updateEmployee = employeeRepository.findById(employeeId).orElseThrow(
                ()  -> new ResourceNotFoundException("Funcionário não encontrado com id: " + employeeId)

        );

        // Atualiza os campos do funcionário com os dados do EmployeeDto
       updateEmployee.setFirstName(updatedEmployeeDto.getFirstName());
       updateEmployee.setLastName(updatedEmployeeDto.getLastName());
       updateEmployee.setEmail(updatedEmployeeDto.getEmail());


        // Salva o funcionário atualizado no repositório e retorna o EmployeeDto correspondente
        // O método save() do repositório salva o objeto Employee atualizado no banco de dados.
        // O método retorna um EmployeeDto, que é uma representação do funcionário atualizado.
        Employee updateEmployeeObj = employeeRepository.save(updateEmployee);

        return EmployeeMapper.mapToEmployeeDto(updateEmployeeObj);

    }

    @Override
    public void deleteEmployee(Long employeeId) {
        EmployeeDto deleteEmployee = getEmployeeById(employeeId);
        if (deleteEmployee == null) {
            throw new ResourceNotFoundException("Funcionário não encontrado com id: " + employeeId);
        }
        employeeRepository.deleteById(employeeId);
    }


    // Métodos de negócio seriam implementados aqui, por exemplo:
    // - createEmployee
    // - getEmployeeById
    // - getAllEmployees
    // - updateEmployee
    // - deleteEmployee

    // Observação: Lembre-se de anotar com @Service para que o Spring gerencie esta classe como um bean de serviço.
}