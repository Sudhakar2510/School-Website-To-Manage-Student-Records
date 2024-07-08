package com.std.SchoolStudentDatabaseManagementSystem.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.std.SchoolStudentDatabaseManagementSystem.dto.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Student findByUsernameAndPassword(String username, String password);
	
	
	

}

