package com.ascent.emp_project.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// This class creates columns in the Database (id, name, phone, email)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Maps the data
@Table(name = "emp_data") // setting name to the table present in the Database
public class EmployeeEntity {
    @Id // Setting id as primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String email;

}
