package com.ascent.emp_project.dao;
import com.ascent.emp_project.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity, Long> {

}
