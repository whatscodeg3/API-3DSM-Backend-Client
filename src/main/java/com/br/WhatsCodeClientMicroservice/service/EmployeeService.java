package com.br.WhatsCodeClientMicroservice.service;

import com.br.WhatsCodeClientMicroservice.dto.EmployeeDto;
import com.br.WhatsCodeClientMicroservice.dto.ReplacementEmployeeDto;
import com.br.WhatsCodeClientMicroservice.models.AuditingLog;
import com.br.WhatsCodeClientMicroservice.models.Client;
import com.br.WhatsCodeClientMicroservice.models.Employee;
import com.br.WhatsCodeClientMicroservice.repository.AuditingLogRepository;
import com.br.WhatsCodeClientMicroservice.repository.EmployeeRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private AuditingLogRepository auditingRepository;

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

    public Employee employeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }
    
    public Employee employeeByIdSecurity(Long id, String email) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeById(id);
        
        var employeeModel = new Employee();
        BeanUtils.copyProperties(employeeDto, employeeModel);
        
        Employee getExistingEmployee = existingEmployee;
        
        if (StringUtils.isEmpty(employeeModel.getName())) {
        	employeeModel.setName(getExistingEmployee.getName());
        }
        if (StringUtils.isEmpty(employeeModel.getEmail())) {
        	employeeModel.setEmail(getExistingEmployee.getEmail());
        }
        if (StringUtils.isEmpty(employeeModel.getCpf())) {
        	employeeModel.setCpf(getExistingEmployee.getCpf());
        }
        if (StringUtils.isEmpty(employeeModel.getRole())) {
        	employeeModel.setRole(getExistingEmployee.getRole());
        }
        if (StringUtils.isEmpty(employeeModel.getPassword())) {
        	employeeModel.setPassword(getExistingEmployee.getPassword());
        }
        employeeModel.setId(getExistingEmployee.getId());
        employeeModel.setDateRegister(getExistingEmployee.getDateRegister());
        employeeModel.setCpf(getExistingEmployee.getCpf());
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employeeModel.setUpdatedBy(employee.getEmail());
        employeeModel.setUpdatedAt(new Date());
        
        return employeeRepository.save(employeeModel);
    }
    
    public Employee replacementEmployee(Long id, ReplacementEmployeeDto replacementEmployeeDto) {
        Employee existingEmployee = employeeById(id);
        
        var employeeModel = new Employee();
        BeanUtils.copyProperties(replacementEmployeeDto, employeeModel);
        
        Employee getExistingEmployee = existingEmployee;
        
        if (StringUtils.isEmpty(employeeModel.getName())) {
        	employeeModel.setName(getExistingEmployee.getName());
        }
        if (StringUtils.isEmpty(employeeModel.getEmail())) {
        	employeeModel.setEmail(getExistingEmployee.getEmail());
        }
        if (StringUtils.isEmpty(employeeModel.getCpf())) {
        	employeeModel.setCpf(getExistingEmployee.getCpf());
        }
        if (StringUtils.isEmpty(employeeModel.getRole())) {
        	employeeModel.setRole(getExistingEmployee.getRole());
        }
        if (StringUtils.isEmpty(employeeModel.getPassword())) {
        	employeeModel.setPassword(getExistingEmployee.getPassword());
        }
        
        employeeModel.setId(getExistingEmployee.getId());
        employeeModel.setDateRegister(getExistingEmployee.getDateRegister());
        employeeModel.setCpf(getExistingEmployee.getCpf());
        Employee employee = (Employee) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        employeeModel.setUpdatedBy(employee.getEmail());
        employeeModel.setUpdatedAt(new Date());
        
        return employeeRepository.save(employeeModel);
    }
    
    public void deleteEmployee(Long id, String employeeName) {       
        AuditingLog auditing = new AuditingLog();
        auditing.setDeletedBy(employeeName);
        auditing.setDeletedAt(new Date());
        auditing.setEntityType("Client");
        auditing.setEntityId(id);
        
        auditingRepository.save(auditing);
        employeeRepository.deleteById(id);
    }

}
