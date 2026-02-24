package com.kunk.orbitra.dto;

import com.kunk.orbitra.enums.ApplicationStatus;
import com.kunk.orbitra.enums.JobType;

import java.time.LocalDateTime;

public class UpdateJobApplicationDTO {
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
}
