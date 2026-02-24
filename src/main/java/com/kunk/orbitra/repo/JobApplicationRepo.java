package com.kunk.orbitra.repo;

import com.kunk.orbitra.enums.ApplicationStatus;
import com.kunk.orbitra.enums.JobType;
import com.kunk.orbitra.model.JobApplication;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JobApplicationRepo extends JpaRepository<JobApplication, Long> {
    @Query("""
            SELECT j
            FROM JobApplication j
            WHERE j.user.email = :email
            AND (
                :query = ''
                OR LOWER(j.companyName) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(j.jobRole) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(j.jobId) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(j.notes) LIKE LOWER(CONCAT('%', :query, '%'))
                OR LOWER(j.source) LIKE LOWER(CONCAT('%', :query, '%')))
            AND (
                :jobType IS NULL
                OR j.jobType = :jobType)
            AND (
                :status IS NULL
                OR j.status = :status)
            """)
    List<JobApplication> searchJobApplications(
            @Param("email") String email,
            @Param("query") String query,
            @Param("jobType") JobType jobType,
            @Param("status") ApplicationStatus status,
            Sort sort
            );
    Optional<JobApplication> findByIdAndUserEmail(Long jobAppId, String email);
}
