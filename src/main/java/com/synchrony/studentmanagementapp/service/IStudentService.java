package com.synchrony.studentmanagementapp.service;

import com.synchrony.studentmanagementapp.entity.Student;
import java.util.List;

public interface IStudentService {
	List<Student> getAllStudents();

	Student getStudentById(Long id);

	Student addStudent(Student student);

	Student updateStudent(Long id, Student studentDetails);

	boolean deleteStudent(Long id);

	List<Student> searchStudentsByName(String name);
}
