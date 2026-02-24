package com.kunk.orbitra.repo;

import com.kunk.orbitra.model.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusHistoryRepo extends JpaRepository<StatusHistory, Long> {
    List<StatusHistory> findByJobApplicationIdAndJobApplicationUserEmailOrderByChangedAtDesc(
            Long jobAppId, String email);
}
