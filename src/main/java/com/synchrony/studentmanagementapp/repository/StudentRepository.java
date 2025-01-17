package com.synchrony.studentmanagementapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synchrony.studentmanagementapp.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

	List<Student> findAll();

	List<Student> findByNameContainingIgnoreCase(String name);
}
