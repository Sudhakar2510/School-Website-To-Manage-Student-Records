package com.std.SchoolStudentDatabaseManagementSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.SchoolStudentDatabaseManagementSystem.dao.AdminDao;
import com.std.SchoolStudentDatabaseManagementSystem.dao.StudentDao;
import com.std.SchoolStudentDatabaseManagementSystem.dto.Admin;
import com.std.SchoolStudentDatabaseManagementSystem.dto.Student;



@Service
public class AdminService {

	@Autowired
	private AdminDao dao;
	@Autowired
	private StudentDao sdao;
	
	
	public String saveAdmin(Admin s) {
		
		Long contact = s.getContact();
		Admin a = dao.fetchByContact(contact);
		
		if(a==null) {
			ArrayList<Student> cList = (ArrayList<Student>) s.getStudents();
			ArrayList<Student> dbList =  (ArrayList<Student>) sdao.findAll();
			ArrayList<Student> out = new ArrayList<>();
			boolean res = true;
			for(Student ans : cList) {
				res = true;
				for(Student db : dbList) {
					if((ans.getContactInfo()).equals(db.getContactInfo())) {
						res = false;
						break;
					}
				}
				if(res) {
					ans.setAdmin(s); 
					out.add(ans);
				}
			}
			s.setStudents(out);
			dao.saveAdmin(s);
			return "Data Saved";
		}
		return "Admin ALready Exists";
	  }

	  
	  public Admin fetchById(long id) {
		  return dao.fetchById(id);
	  }
	  
	  public String deleteById(long id) {
		  return dao.deleteById(id);
	  }
	  
	  public List<Admin> fetchAll(){
		  return dao.findAll();
	  }
	  
	  public Admin updateGrade(long id, String password ) {
		  Admin a=fetchById(id);
		  
		  if (a!=null) {
			a.setPassword(password);
			
			return dao.updateGrade(a);
		}return null;
	  }

	  public Admin updateAll(Long id, Admin newAdmin) {
		  Admin existingAdmin = fetchById(id);
		  
		  if (existingAdmin!=null) {
			newAdmin.setId(id);
			
			return dao.updateGrade(newAdmin);
			
		}return null;
	  }

	public Admin getAdminByUsernameAndPassword(String username, String password) {
		return dao.getAdminByUsernameAndPassword(username, password);
	}


	
	
	
}
