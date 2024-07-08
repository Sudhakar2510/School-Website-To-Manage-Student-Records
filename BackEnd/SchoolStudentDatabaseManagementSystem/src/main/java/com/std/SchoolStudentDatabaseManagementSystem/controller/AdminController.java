package com.std.SchoolStudentDatabaseManagementSystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.std.SchoolStudentDatabaseManagementSystem.dto.Admin;
import com.std.SchoolStudentDatabaseManagementSystem.dto.ApiResponse;
import com.std.SchoolStudentDatabaseManagementSystem.service.AdminService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class AdminController {

    @Autowired
    private AdminService service;

    @PostMapping("saveAdmin")
    public ResponseEntity<ApiResponse<String>> saveAdmin(@RequestBody Admin a) {
        String message = service.saveAdmin(a);
        return new ResponseEntity<>(new ApiResponse<>("Admin saved successfully", message, true), HttpStatus.CREATED);
    }

    @GetMapping("fetchAdminbyid")
    public ResponseEntity<ApiResponse<Admin>> fetchAdminById(@RequestParam long id) {
        Admin admin = service.fetchById(id);
        if (admin != null) {
            return new ResponseEntity<>(new ApiResponse<>("Admin fetched successfully", admin, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>("Admin not found", null, false), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteAdminByid")
    public ResponseEntity<ApiResponse<String>> deleteById(@RequestParam long id) {
        String message = service.deleteById(id);
        return new ResponseEntity<>(new ApiResponse<>(message, null, true), HttpStatus.OK);
    }

    @GetMapping("fetchAll")
    public ResponseEntity<ApiResponse<List<Admin>>> fetchAllAdmin() {
        List<Admin> admins = service.fetchAll();
        return new ResponseEntity<>(new ApiResponse<>("All admins fetched successfully", admins, true), HttpStatus.OK);
    }

    @PutMapping("updateAdmin/{id}/{password}")
    public ResponseEntity<ApiResponse<Admin>> updateGrade(@PathVariable long id, @PathVariable String password) {
        Admin admin = service.updateGrade(id, password);
        return new ResponseEntity<>(new ApiResponse<>("Password updated successfully", admin, true), HttpStatus.OK);
    }

    @PutMapping("updateAll/{id}")
    public ResponseEntity<ApiResponse<Admin>> updateAll(@PathVariable Long id, @RequestBody Admin a) {
        Admin updatedAdmin = service.updateAll(id, a);
        return new ResponseEntity<>(new ApiResponse<>("Admin updated successfully", updatedAdmin, true), HttpStatus.OK);
    }

    
    @GetMapping("/adminLogin")
    public ResponseEntity<ApiResponse<Admin>> login(@RequestParam String username, @RequestParam String password) {
        Admin admin = service.getAdminByUsernameAndPassword(username, password);
        if (admin != null) {
            return new ResponseEntity<>(new ApiResponse<>("Login successful", admin, true), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse<>("Invalid credentials", null, false), HttpStatus.UNAUTHORIZED);
        }
    }
    
  
    
    
    
}
