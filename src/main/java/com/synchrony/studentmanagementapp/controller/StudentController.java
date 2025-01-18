package com.synchrony.studentmanagementapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.synchrony.studentmanagementapp.entity.Student;
import com.synchrony.studentmanagementapp.service.IStudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;


    @GetMapping
    public ResponseEntity<List<Student>> getStudents(@RequestParam(value = "name", required = false) String name) {
        List<Student> students = (name != null && !name.isEmpty()) 
            ? studentService.searchStudentsByName(name) 
            : studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMessages = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errorMessages.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@PathVariable Long id, @RequestBody @Valid Student student, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMessages = new HashMap<>();
            result.getFieldErrors().forEach(error -> {
                errorMessages.put(error.getField(), error.getDefaultMessage());
            });
            return new ResponseEntity<>(errorMessages, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        boolean isDeleted = studentService.deleteStudent(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
