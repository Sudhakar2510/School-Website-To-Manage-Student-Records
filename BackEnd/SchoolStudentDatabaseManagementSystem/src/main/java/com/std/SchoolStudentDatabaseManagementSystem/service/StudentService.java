package com.std.SchoolStudentDatabaseManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.std.SchoolStudentDatabaseManagementSystem.dao.AdminDao;
import com.std.SchoolStudentDatabaseManagementSystem.dao.StudentDao;
import com.std.SchoolStudentDatabaseManagementSystem.dto.Admin;
import com.std.SchoolStudentDatabaseManagementSystem.dto.Student;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao dao;

    @Autowired
    private AdminDao adao;

    public Admin saveAdmin(long id, Student s) {
        Admin a = adao.fetchById(id);
        if (a != null) {
            List<Student> list = a.getStudents();
            list.add(s);
            s.setAdmin(a);
            adao.saveAdmin(a);
            return a;
        }
        return null;
    }

    public Student fetchById(long id) {
        return dao.fetchById(id);
    }

    public String deleteById(long id) {
        return dao.deleteById(id);
    }

    public List<Student> fetchAll() {
        return dao.findAll();
    }

    public Student updateGrade(long id, String grade) {
        Student s = fetchById(id);
        if (s != null) {
            s.setGrade(grade);
            return dao.updateGrade(s);
        }
        return null;
    }

    public Student updateAll(Long id, Student newStudent) {
        Student existingStudent = fetchById(id);
        if (existingStudent != null) {
            newStudent.setId(id);
            return dao.updateGrade(newStudent);
        }
        return null;
    }

    public Student getStudentByUsernameAndPassword(String username, String password) {
        return dao.getStudentByUsernameAndPassword(username, password);
    }


    
    public Student save(Student student) {
        return dao.save(student);
    }

}
