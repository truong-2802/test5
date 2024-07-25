package com.example.test5.service;

import com.example.test5.entity.Employee;
import com.example.test5.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Page<Employee> listAllEmployees(int pageNo, int pageSize, String sortField, String sortDir, String fullName, Integer age, Double minSalary, Double maxSalary) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortField));
        // Add custom filtering logic if needed
        return employeeRepository.findAll(pageable);
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Double getMaxSalary() {
        // Implement logic to get the maximum salary
        return 100000.0; // Example value
    }

    public Page<Employee> searchEmployee(String keyword, int pageNo, int pageSize, String sortField, String sortDir) {
        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, Sort.by(Sort.Direction.fromString(sortDir), sortField));
        return employeeRepository.findAll(pageable); // Implement search logic if needed
    }
}
