package com.kunk.orbitra.dto;

import java.util.List;

public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
//    private List<JobApplicationResponseDTO> jobApplications;


    public void setId(Long id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
//    public void setJobApplications(List<JobApplicationResponseDTO> jobApplications) {
//        this.jobApplications = jobApplications;
//    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }
//    public List<JobApplicationResponseDTO> getJobApplications() { return jobApplications; }
}
