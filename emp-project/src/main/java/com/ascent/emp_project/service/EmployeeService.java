package com.ascent.emp_project.service;
import com.ascent.emp_project.model.Employee;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public interface EmployeeService {
    String createEmployee (Employee employee);
    List<Employee> readEmployees();
    boolean deleteEmployee(Long id);
    String updateEmployee(Long id, Employee employee);
    Employee readEmployee(Long id);
}
