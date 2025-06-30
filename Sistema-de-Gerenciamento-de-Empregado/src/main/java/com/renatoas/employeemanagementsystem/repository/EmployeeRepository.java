package com.renatoas.employeemanagementsystem.repository;

import com.renatoas.employeemanagementsystem.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interface de repositório para gerenciar operações CRUD da entidade Employee.
 * Extende JpaRepository para fornecer métodos prontos para uso, como salvar, buscar, atualizar e deletar funcionários.
 * Utiliza a entidade Employee e o tipo Long para o ID.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
