package com.ascent.emp_project.controller;
import com.ascent.emp_project.model.Employee;
import com.ascent.emp_project.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
@RestController
public class EmpController {
   @Autowired
     EmployeeService employeeService;  // Dependency Injection
    @GetMapping("employees")
    public List<Employee> getAllEmployees(){
        return employeeService.readEmployees();
    }
    @GetMapping("employee/{id}")
    public Employee getAllEmployeeById(@PathVariable Long id){
        return employeeService.readEmployee(id);
    }
    @PostMapping("/addEmployees")
    public String createEmployee(@RequestBody Employee employee){
        return employeeService.createEmployee(employee);

    }
//    @DeleteMapping("employee/{id}")
//    public String deleteEmployee(@PathVariable Long id){
//        if(employeeService.deleteEmployee(id));
//        return "Deleted Successfully";
//    }
    @DeleteMapping("employee/{id}")
    public String deleteEmployee(@PathVariable Long id){
        boolean deleted = employeeService.deleteEmployee(id);
        if (deleted) {
            return "Deleted Successfully";
        } else {
            return "Employee with ID " + id + " not found";
        }
    }
    @PutMapping("employee/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee){
        return employeeService.updateEmployee(id, employee);
    }
}
