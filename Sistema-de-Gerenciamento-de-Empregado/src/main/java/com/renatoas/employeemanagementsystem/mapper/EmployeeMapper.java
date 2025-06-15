package com.renatoas.employeemanagementsystem.mapper;

import com.renatoas.employeemanagementsystem.dto.EmployeeDto;
import com.renatoas.employeemanagementsystem.entity.Employee;

/**
 * Mapper class to convert between Employee and EmployeeDto objects.
 * This class provides methods to map an Employee entity to an EmployeeDto
 * and vice versa.
 */
public class EmployeeMapper {


    // Converts an Employee entity to an EmployeeDto.
    // @param employee the Employee entity to be converted
    public static EmployeeDto mapToEmployeeDto (Employee employee) {

        return new EmployeeDto(
                employee.getId(),
                //TODO - Implementar o método toString() na classe Employee para retornar uma representação legível do objeto.
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    // Converts an EmployeeDto to an Employee entity.
    public static Employee mapToEmployee (EmployeeDto employeeDto) {

        //TODO - Implementar a validação dos dados do EmployeeDto antes de criar o Employee.
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
