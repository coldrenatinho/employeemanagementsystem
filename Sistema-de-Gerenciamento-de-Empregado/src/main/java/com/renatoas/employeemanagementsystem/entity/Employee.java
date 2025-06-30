package com.renatoas.employeemanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombook: Gera automaticamente os métodos getters, setters, construtores e toString
@Getter
@Setter
@NoArgsConstructor // Gera um construtor sem argumentos
@AllArgsConstructor // Gera um construtor com todos os argumentos

// JPA: Anotações para mapear a classe como uma entidade do banco de dados
@Entity
// Define o nome da tabela no banco de dados
@Table(name = "Employee")
public class Employee {

    // JPA: Define o ID da entidade, que será gerado automaticamente
    //FIXME: Use @GeneratedValue para especificar a estratégia de geração do ID PARA UUID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email_id",nullable = false, unique = true )
    private String email;

}
