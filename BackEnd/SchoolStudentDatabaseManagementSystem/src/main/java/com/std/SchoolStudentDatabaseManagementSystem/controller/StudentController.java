package com.std.SchoolStudentDatabaseManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.std.SchoolStudentDatabaseManagementSystem.dto.Admin;
import com.std.SchoolStudentDatabaseManagementSystem.dto.ApiResponse;
import com.std.SchoolStudentDatabaseManagementSystem.dto.Student;
import com.std.SchoolStudentDatabaseManagementSystem.service.StudentService;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("saveStudent")
    public ResponseEntity<ApiResponse<Admin>> saveStudent(@RequestParam long id, @RequestBody Student s) {
        Admin admin = studentService.saveAdmin(id, s);
        return new ResponseEntity<>(new ApiResponse<>("Student saved successfully", admin, true), HttpStatus.CREATED);
    }

    @GetMapping("fetchStudentbyid")
    public ResponseEntity<ApiResponse<Student>> fetchStudentById(@RequestParam long id) {
        Student student = studentService.fetchById(id);
        return new ResponseEntity<>(new ApiResponse<>("Student fetched successfully", student, true), HttpStatus.OK);
    }

    @DeleteMapping("deleteStudentbyid")
    public ResponseEntity<ApiResponse<String>> deleteById(@RequestParam long id) {
        String message = studentService.deleteById(id);
        return new ResponseEntity<>(new ApiResponse<>(message, null, true), HttpStatus.OK);
    }

    @GetMapping("getAll")
    public ResponseEntity<ApiResponse<List<Student>>> fetchAllStudent() {
        List<Student> students = studentService.fetchAll();
        return new ResponseEntity<>(new ApiResponse<>("All students fetched successfully", students, true), HttpStatus.OK);
    }

    @PutMapping("updateStudent/{id}/{grade}")
    public ResponseEntity<ApiResponse<Student>> updateGrade(@PathVariable long id, @PathVariable String grade) {
        Student student = studentService.updateGrade(id, grade);
        return new ResponseEntity<>(new ApiResponse<>("Grade updated successfully", student, true), HttpStatus.OK);
    }

//    @PutMapping("modifyAll/{id}")
//    public ResponseEntity<ApiResponse<Student>> updateAll(@PathVariable Long id, @RequestBody Student s) {
//        Student updatedStudent = studentService.updateAll(id, s);
//        return new ResponseEntity<>(new ApiResponse<>("Student updated successfully", updatedStudent, true), HttpStatus.OK);
//    }
    
    @PutMapping("modifyAll/{id}")
    public ResponseEntity<ApiResponse<Student>> updateAll(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> existingStudentOpt = Optional.of(studentService.fetchById(id));
        if (!existingStudentOpt.isPresent()) {
            return new ResponseEntity<>(new ApiResponse<>("Student not found", null, false), HttpStatus.NOT_FOUND);
        }

        Student existingStudent = existingStudentOpt.get();
        // Preserve the admin ID from the existing student
        updatedStudent.setAdmin(existingStudent.getAdmin());

        Student savedStudent = studentService.save(updatedStudent); // Ensure your service method returns the saved entity correctly
        return new ResponseEntity<>(new ApiResponse<>("Student updated successfully", savedStudent, true), HttpStatus.OK);
    }



    @GetMapping("/studentLogin")
    public ResponseEntity<ApiResponse<Student>> login(@RequestParam String username, @RequestParam String password) {
        Student student = studentService.getStudentByUsernameAndPassword(username, password);
        if (student != null) {
            return new ResponseEntity<>(new ApiResponse<>("Login successful", student, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>("Invalid credentials", null, false), HttpStatus.UNAUTHORIZED);
        }
    }
}
