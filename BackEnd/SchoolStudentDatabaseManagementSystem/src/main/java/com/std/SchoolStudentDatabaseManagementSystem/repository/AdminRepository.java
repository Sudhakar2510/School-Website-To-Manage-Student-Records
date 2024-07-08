package com.std.SchoolStudentDatabaseManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.std.SchoolStudentDatabaseManagementSystem.dto.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{
	public Admin findByContact(Long contact);
	
	Admin findByUsernameAndPassword(String username, String password);
	
	 
}
