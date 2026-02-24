package com.kunk.orbitra.dto;

import com.kunk.orbitra.enums.ApplicationStatus;

import java.time.LocalDateTime;

public class StatusHistoryResponseDTO {
    private Long id;
    private ApplicationStatus oldStatus;
    private ApplicationStatus newStatus;
    private LocalDateTime changedAt;

    public void setId(Long id) {
        this.id = id;
    }

    public void setOldStatus(ApplicationStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public void setNewStatus(ApplicationStatus newStatus) {
        this.newStatus = newStatus;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public Long getId() {
        return id;
    }

    public ApplicationStatus getOldStatus() {
        return oldStatus;
    }

    public ApplicationStatus getNewStatus() {
        return newStatus;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }
}
