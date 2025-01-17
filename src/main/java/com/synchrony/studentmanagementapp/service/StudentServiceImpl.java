package com.synchrony.studentmanagementapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synchrony.studentmanagementapp.entity.Student;
import com.synchrony.studentmanagementapp.repository.StudentRepository;

import jakarta.annotation.PostConstruct;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        return studentRepository.findById(id).map(student -> {
            student.setName(studentDetails.getName());
            student.setAge(studentDetails.getAge());
            student.setStudentClass(studentDetails.getStudentClass());
            student.setPhoneNumber(studentDetails.getPhoneNumber());
            return studentRepository.save(student);
        }).orElse(null);
    }

    @Override
    public boolean deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    // Add default student records at application startup
    @PostConstruct
    public void initializeStudents() {
        if (studentRepository.count() == 0) {
            studentRepository.save(new Student(null, "Rajendar", "24", "12th Grade", "1234567890"));
            studentRepository.save(new Student(null, "Bantu", "42", "11th Grade", "678543212"));
            studentRepository.save(new Student(null, "Pushpa", "32", "19th Grade", "234567876"));
        }
    }

	@Override
	public Student getStudentById(Long id) {
		 return studentRepository.findById(id).orElse(null);
	}
	
	@Override
	public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContainingIgnoreCase(name); 
    }
}
