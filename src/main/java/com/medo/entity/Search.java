package com.medo.entity;


public class Search {
    private Long id;
    private String name;
    private String type;  // "Doctor" or "Clinic"
    private String details; // Specialization for doctors, Location for clinics

    public Search(Long id, String name, String type, String details) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.details = details;
    }

  
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}

