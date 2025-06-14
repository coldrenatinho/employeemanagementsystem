package com.renatoas.employeemanagementsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) para transferência de dados do funcionário entre as camadas da aplicação.
 * Utilizado para transportar informações de forma segura e controlada, evitando expor diretamente a entidade do banco.
 * Permite personalizar os dados enviados ou recebidos pela API, facilitando validações e manutenções futuras.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
