package com.ascent.emp_project.service;

import com.ascent.emp_project.dao.EmployeeRepo;
import com.ascent.emp_project.entity.EmployeeEntity;
import com.ascent.emp_project.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


public class testEmployeeServiceImpl {

    //   @Autowired EmployeeRepo employeeRepo;
    @Mock
    EmployeeRepo employeeRepo;
    EmployeeServiceImpl employeeService;

    @BeforeEach
    public void setUp() {
        employeeRepo = Mockito.mock(EmployeeRepo.class);
        employeeService = new EmployeeServiceImpl(employeeRepo);
    }

    @Test
    public void testCreateEmployee() {
        // Create a mock Employee object for testing
        Employee employee = new Employee(1L, "Rohit", "9900990099", "rohit@gmail.com");
        // Create a mock EmployeeEntity that will be returned by the repository save method
        EmployeeEntity savedEntity = new EmployeeEntity();

        // Mocking the saved entity with an ID
        savedEntity.setId(1L);
        // Mocking the save method of employeeRepo to return the savedEntity
        when(employeeRepo.save(any(EmployeeEntity.class))).thenReturn(savedEntity);

        // Calling the createEmployee method of employeeService with the mock employee
        String result = employeeService.createEmployee(employee);
        // Asserting that the result of createEmployee method is "Saved Successfully"
        assertEquals("Saved Successfully", result);

        // Verifying that the save method of employeeRepo was called at least once with any EmployeeEntity
        verify(employeeRepo, atLeastOnce()).save(any(EmployeeEntity.class));
    }

    @Test
    public void testReadEmployee()
    {
        // Mock employee ID for testing
        long id = 1L;

        // Create a mock EmployeeEntity object for testing
        EmployeeEntity employeeEntity = new EmployeeEntity(id, "Rohit", "9900990099", "rohit@gmail.com");

        // Mocking the findById method of employeeRepo to return an Optional containing employeeEntity
        when(employeeRepo.findById(id)).thenReturn(Optional.of(employeeEntity));

        // Calling the readEmployee method of employeeService with the mock employee ID
        Employee employee = employeeService.readEmployee(id);

        // Asserting that the returned employee object is not null
        assertNotNull(employee);

        // Asserting specific attributes of the employee object
        assertNotNull("Rohit", employee.getName());
        assertNotNull("9900990099", employee.getPhone());
        assertNotNull("rohit@gmail.com", employee.getEmail());

        // Verifying that the findById method of employeeRepo was called at least once with the mock employee ID
        verify(employeeRepo, atLeastOnce()).findById(id);
    }

    @Test
    public void testReadEmployees()
    {
        List<EmployeeEntity> employeeEntities = new ArrayList<>();
        employeeEntities.add(new EmployeeEntity(1L, "Rohit", "99009900990", "rohit@gmail.com"));

        when(employeeRepo.findAll()).thenReturn(employeeEntities);

        List<Employee> employees = employeeService.readEmployees();
        assertEquals(1, employees.size());
        assertEquals("Rohit", employees.get(0).getName());
        assertEquals("rohit@gmail.com", employees.get(0).getEmail());
        assertEquals("99009900990", employees.get(0).getPhone());

        verify(employeeRepo, atLeastOnce()).findAll();
    }

    @Test
    public void testReadEmployeeNotFound() {
    Long id = 1L;
    when(employeeRepo.findById(id)).thenReturn(Optional.empty());
    Employee employee = employeeService.readEmployee(id);
    assertNull(employee);
    verify(employeeRepo, atLeastOnce()).findById(id);
    }

        @Test
        public void testDeleteEmployee(){
            Long id = 1L;
            EmployeeEntity employeeEntity = new EmployeeEntity(id, "Rohit", "99009900990", "rohit@gmail.com");

            when(employeeRepo.findById(id)).thenReturn(Optional.of(employeeEntity));
            boolean result = employeeService.deleteEmployee(id);

            assertTrue(result);
            verify(employeeRepo, atLeastOnce()).findById(id);
            verify(employeeRepo, atLeastOnce()).delete(employeeEntity);
        }

    @Test
    public void testDeleteEmployeeNotFound(){
     Long id = 1L;

     when(employeeRepo.findById(id)).thenReturn(Optional.empty());
     boolean result = employeeService.deleteEmployee(id);

     assertFalse(result);

     verify(employeeRepo, atLeastOnce()).findById(id);
     verify(employeeRepo, never()).delete(any());
    }

    @Test
    public void testUpdateEmployee(){
        Long id = 1L;
        Employee updatedEmployee = new Employee(1L, "Jane Smith", "99009900990", "jane.smith@example.com");
        EmployeeEntity existingEntity = new EmployeeEntity(id, "Jane Smith", "99009900990", "jane.smith@example.com" );

        when(employeeRepo.findById(id)).thenReturn(Optional.of(existingEntity));
        when(employeeRepo.save(any(EmployeeEntity.class))).thenReturn(existingEntity);

        String result = employeeService.updateEmployee(id, updatedEmployee);
        assertEquals("Updated Successfully", result);
        assertEquals("Jane Smith", existingEntity.getName());
        assertEquals("jane.smith@example.com", existingEntity.getEmail());
        assertEquals("99009900990", existingEntity.getPhone());

        verify(employeeRepo, atLeastOnce()).findById(id);
        verify(employeeRepo, atLeastOnce()).save(existingEntity);
    }
}


