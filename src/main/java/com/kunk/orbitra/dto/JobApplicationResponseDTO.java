package com.kunk.orbitra.dto;

import com.kunk.orbitra.enums.ApplicationStatus;
import com.kunk.orbitra.enums.JobType;

import java.time.LocalDateTime;

public class JobApplicationResponseDTO {
    private Long id;
    private String companyName;
    private String jobRole;
    private String jobId;
    private JobType jobType;
    private String applyLink;
    private String companyProfileLink;
    private ApplicationStatus status;
    private String source;
    private LocalDateTime dateApplied;
    private String notes;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public void setId(Long id) {
        this.id = id;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }
    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }
    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }
    public void setCompanyProfileLink(String companyProfileLink) {
        this.companyProfileLink = companyProfileLink;
    }
    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public void setDateApplied(LocalDateTime dateApplied) {
        this.dateApplied = dateApplied;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public String getJobId() {
        return jobId;
    }

    public JobType getJobType() {
        return jobType;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public String getCompanyProfileLink() {
        return companyProfileLink;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public String getSource() {
        return source;
    }

    public LocalDateTime getDateApplied() {
        return dateApplied;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
