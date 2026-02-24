package com.kunk.orbitra.model;

import com.kunk.orbitra.enums.ApplicationStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "status_history")
public class StatusHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "old_status")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus oldStatus;

    @Column(name = "new_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus newStatus;

    @Column(name = "changed_at")
    private LocalDateTime changedAt;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "job_id", nullable = false)
    private JobApplication jobApplication;

    public StatusHistory() {
    }

    public StatusHistory(ApplicationStatus oldStatus, ApplicationStatus newStatus) {
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
    }

    @PrePersist
    protected void onChange() {
        this.changedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public ApplicationStatus getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(ApplicationStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public ApplicationStatus getNewStatus() {
        return newStatus;
    }

    public void setNewStatus(ApplicationStatus newStatus) {
        this.newStatus = newStatus;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }


    public JobApplication getJobApplication() {
        return jobApplication;
    }

    public void setJobApplication(JobApplication jobApplication) {
        this.jobApplication = jobApplication;
    }
}
