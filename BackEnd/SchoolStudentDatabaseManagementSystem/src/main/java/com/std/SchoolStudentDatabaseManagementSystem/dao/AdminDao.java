package com.std.SchoolStudentDatabaseManagementSystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.std.SchoolStudentDatabaseManagementSystem.dto.Admin;
import com.std.SchoolStudentDatabaseManagementSystem.repository.AdminRepository;
import com.std.SchoolStudentDatabaseManagementSystem.repository.StudentRepository;

@Repository
public class AdminDao {

	@Autowired
	AdminRepository repo;
	
	@Autowired
	StudentRepository srepo;
	
	public Admin saveAdmin(Admin a) {
		return repo.save(a);
	}
	
	public Admin fetchById(Long id) {
		 Optional<Admin> o= repo.findById(id);
		 
		 if (o.isPresent()) {
			return o.get();
		}return null;
	}
	
	public List<Admin> findAll(){
		return repo.findAll();
	}
	
	public String deleteById(Long id) {
		repo.deleteById(id);
		
		return "The ID: "+id+" has been deleted";
	}
	
	public Admin updateGrade(Admin a) {
		return repo.save(a);
	}
	
	public Admin fetchByContact(Long contact) {
		return repo.findByContact(contact);
	}
	
	public Admin getAdminByUsernameAndPassword(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }
	

	
	
	
}
