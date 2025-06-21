package com.renatoas.employeemanagementsystem.service;

import com.renatoas.employeemanagementsystem.dto.EmployeeDto;

/**
 * Interface da camada de serviço responsável por definir os métodos de negócio relacionados ao funcionário.
 * A camada de serviço faz a ponte entre os controladores (controllers) e os repositórios, garantindo que as regras de negócio sejam aplicadas corretamente.
 * No Spring Boot, as implementações desta interface são anotadas com @Service para serem gerenciadas pelo framework.
 * Essa separação facilita a manutenção, testes e reutilização do código.
 */
public interface EmployeeService {

    EmployeeDto createEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);
}
