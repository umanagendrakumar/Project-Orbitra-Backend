package com.kunk.orbitra.model;

import com.kunk.orbitra.enums.ApplicationStatus;
import com.kunk.orbitra.enums.JobType;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job_applications")
public class JobApplication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @Column(name = "job_role", nullable = false, length = 100)
    private String jobRole;

    @Column(name = "job_id", length = 50)
    private String jobId;

    @Enumerated(EnumType.STRING) // @Enumerated tells Hibernate the storage strategy.
    @Column(name = "job_type", nullable = false, length = 50)
    private JobType jobType;

    @Column(name = "apply_link", columnDefinition = "TEXT")
    private String applyLink;

    @Column(name = "company_profile_link", columnDefinition = "TEXT")
    private String companyProfileLink;

    @Enumerated(EnumType.STRING)
    @Column(length = 50, nullable = false)
    private ApplicationStatus status;

    @Column(length = 20)
    private String source;

    @Column(name = "date_applied")
    private LocalDateTime dateApplied;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "jobApplication", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StatusHistory> statusHistories;



    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobRole() {
        return jobRole;
    }

    public void setJobRole(String jobRole) {
        this.jobRole = jobRole;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public JobType getJobType() {
        return jobType;
    }

    public void setJobType(JobType jobType) {
        this.jobType = jobType;
    }

    public String getApplyLink() {
        return applyLink;
    }

    public void setApplyLink(String applyLink) {
        this.applyLink = applyLink;
    }

    public String getCompanyProfileLink() {
        return companyProfileLink;
    }

    public void setCompanyProfileLink(String companyProfileLink) {
        this.companyProfileLink = companyProfileLink;
    }

    public ApplicationStatus  getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus  status) {
        this.status = status;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getDateApplied() {
        return dateApplied;
    }

    public void setDateApplied(LocalDateTime dateApplied) {
        this.dateApplied = dateApplied;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public List<StatusHistory> getStatusHistories() {
        return statusHistories;
    }

    public void setStatusHistories(List<StatusHistory> statusHistories) {
        this.statusHistories = statusHistories;
    }
}
