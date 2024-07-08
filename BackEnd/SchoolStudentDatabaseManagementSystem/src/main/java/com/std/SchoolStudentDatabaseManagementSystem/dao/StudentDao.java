package com.std.SchoolStudentDatabaseManagementSystem.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.std.SchoolStudentDatabaseManagementSystem.dto.Student;
import com.std.SchoolStudentDatabaseManagementSystem.repository.StudentRepository;

@Repository
public class StudentDao {
    @Autowired
    StudentRepository repo;

    public Student fetchById(Long id) {
        Optional<Student> o = repo.findById(id);
        return o.orElse(null);
    }

    public List<Student> findAll() {
        return repo.findAll();
    }

    public String deleteById(Long id) {
        Optional<Student> student = repo.findById(id);
        if (student.isPresent()) {
            repo.deleteById(id);
            return "The ID: " + id + " has been deleted";
        }
        return "Student not found";
    }

    public Student updateGrade(Student s) {
        return repo.save(s);
    }

    public Student getStudentByUsernameAndPassword(String username, String password) {
        return repo.findByUsernameAndPassword(username, password);
    }

	public Student save(Student student) {
		// TODO Auto-generated method stub
		return repo.save(student);
	}
    
}
