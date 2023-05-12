package com.br.WhatsCodeClientMicroservice.controller;

import com.br.WhatsCodeClientMicroservice.dto.EmployeeDto;
import com.br.WhatsCodeClientMicroservice.models.Employee;
import com.br.WhatsCodeClientMicroservice.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/funcionario")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('Administrador')")
    public ResponseEntity<Employee> createEmployee(@RequestBody EmployeeDto employeeDto) {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        employeeDto.setCreatedBy(employee.getEmail());

        employeeDto.setCreatedAt(new Date());

        Employee createdEmployee = employeeService.createEmployee(employeeDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('Administrador')")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> FindAllEmployees = employeeService.getAllEmployees();
        return ResponseEntity.ok().body(FindAllEmployees);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador')")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Integer id) {
        Employee FindOneEmployee = employeeService.employeeById(id);
        if (FindOneEmployee != null) {
            return ResponseEntity.ok().body(FindOneEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador')")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody EmployeeDto employeeDto) {
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDto);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('Administrador')")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Integer id) {
        Employee employee = employeeService.employeeById(id);
        employeeService.deleteEmployee(id);
      
        return ResponseEntity.noContent().build();
    }
}

