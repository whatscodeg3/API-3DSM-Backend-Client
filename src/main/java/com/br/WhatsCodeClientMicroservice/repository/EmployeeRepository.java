package com.br.WhatsCodeClientMicroservice.repository;

import com.br.WhatsCodeClientMicroservice.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Employee findByCpf(String cpf);
}
