package com.br.WhatsCodeClientMicroservice.service;

import com.br.WhatsCodeClientMicroservice.dto.EmployeeDto;
import com.br.WhatsCodeClientMicroservice.models.Client;
import com.br.WhatsCodeClientMicroservice.models.Employee;
import com.br.WhatsCodeClientMicroservice.repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeDto employeeDto) {
        Employee employeeModel = new Employee();
        BeanUtils.copyProperties(employeeDto, employeeModel);

        BCryptPasswordEncoder criptografar = new BCryptPasswordEncoder();

        String senhaCriptografada = criptografar.encode(employeeModel.getPassword());

        employeeModel.setPassword(senhaCriptografada);
        return employeeRepository.save(employeeModel);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee employeeById(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Integer id, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeById(id);
        Employee newEmployee = new Employee();
        BeanUtils.copyProperties(employeeDto, existingEmployee);
        return employeeRepository.save(existingEmployee);
    }
    
    public void deleteEmployee(Integer id) {
        Employee existingEmployee = employeeById(id);
        employeeRepository.delete(existingEmployee);
    }

}
