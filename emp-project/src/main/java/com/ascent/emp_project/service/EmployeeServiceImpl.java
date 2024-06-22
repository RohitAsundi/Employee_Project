package com.ascent.emp_project.service;
import com.ascent.emp_project.dao.EmployeeRepo;
import com.ascent.emp_project.entity.EmployeeEntity;
import com.ascent.emp_project.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepo.save(employeeEntity);
        return "Saved Successfully";
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeeList = employeeRepo.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity: employeeList) {
            Employee emp = new Employee();
            BeanUtils.copyProperties(employeeEntity, emp);
            employees.add(emp);
        }
        return employees;
    }

    @Override
    public boolean deleteEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepo.findById(id);
        if (!optionalEmployee.isPresent()) {
            return false;
        } else {
            employeeRepo.delete(optionalEmployee.get());
            return true;
        }
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        EmployeeEntity existingEmployee = employeeRepo.findById(id).get();
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setName(employee.getName());
        existingEmployee.setPhone(employee.getPhone());
        employeeRepo.save(existingEmployee);
        return "Updated Successfully";
    }

    @Override
    public Employee readEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepo.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employeeEntity = optionalEmployee.get();
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeEntity, employee);
            return employee;
        } else {
            return null;
        }
    }
}
