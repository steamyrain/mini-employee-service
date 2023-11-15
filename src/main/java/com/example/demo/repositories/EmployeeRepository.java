package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.dtos.IEmployeeProjection;
import com.example.demo.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{

	@Query(nativeQuery = true, value = "WITH RECURSIVE managers AS ( "+
		"SELECT * FROM employee where id = :employeeId "+
		"UNION ALL "+
		"SELECT e.* FROM employee e INNER JOIN managers m ON m.manager_id = e.id ) "+
		"SELECT name, email, position FROM managers WHERE id <> :employeeId")
	List<IEmployeeProjection> findAllManagerRcv(@Param("employeeId")String employeeId);
}
