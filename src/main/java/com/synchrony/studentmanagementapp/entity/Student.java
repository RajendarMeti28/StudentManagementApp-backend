package com.synchrony.studentmanagementapp.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @Min(1)
    @Max(100)
    @Column(nullable = false)
    private String age;

    @Column(nullable = false)
    private String studentClass;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public Student() {
    	
    }
    
    public Student(Long id, String name, String age, String studentClass, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.studentClass = studentClass;
        this.phoneNumber = phoneNumber;
    }
}
