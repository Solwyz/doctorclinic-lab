package com.medo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import javax.persistence.*;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bookingFor;  // Self,Other Person
    private String gender;
    private int age;
    
    @Column(name = "problem") 
    private String problem;

    public Patient() {}

    public Patient(Long id, String name, String gender, int age, String problem, String bookingFor) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.problem = problem;
        this.bookingFor = bookingFor;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBookingFor() { return bookingFor; }
    public void setBookingFor(String bookingFor) { this.bookingFor = bookingFor; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getProblem() { return problem; }
    public void setProblem(String problem) { this.problem = problem; }
}
