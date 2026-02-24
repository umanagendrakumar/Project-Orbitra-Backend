package com.kunk.orbitra.controller;

import com.kunk.orbitra.dto.CreateJobApplicationDTO;
import com.kunk.orbitra.dto.JobApplicationResponseDTO;
import com.kunk.orbitra.dto.StatusHistoryResponseDTO;
import com.kunk.orbitra.dto.UpdateJobApplicationDTO;
import com.kunk.orbitra.enums.ApplicationStatus;
import com.kunk.orbitra.enums.JobType;
import com.kunk.orbitra.service.JobApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobApplicationController {
    @Autowired
    private JobApplicationService service;

    @GetMapping("/job-applications")
    public List<JobApplicationResponseDTO> getAllOrSearchJobApps(
            @RequestParam(required = false) String query,
            @RequestParam(required = false) JobType jobType,
            @RequestParam(required = false) ApplicationStatus status,
            @RequestParam(defaultValue = "DESC") Sort.Direction direction,
            Authentication authentication) {
        return service.getAllOrSearchJobApps(query, jobType, status, direction, authentication);
    }
    @PostMapping("/job-applications")
    public String addJobApp(
            @RequestBody CreateJobApplicationDTO dto,
            Authentication authentication) {
        service.addJobApp(dto, authentication);
        return "New Job Application Added";
    }

    @PatchMapping("/job-applications/{jobAppId}")
    public JobApplicationResponseDTO updateJobApp(
            @PathVariable Long jobAppId,
            @RequestBody UpdateJobApplicationDTO dto,
            Authentication authentication) {
        return service.updateJobApp(jobAppId, dto, authentication);
    }

    @DeleteMapping("/job-applications/{jobAppId}")
    public String deleteJobApp(@PathVariable Long jobAppId, Authentication authentication) {
        service.deleteJobApp(jobAppId, authentication);
        return "Job Application Deleted";
    }

    @GetMapping("/status-history/{jobAppId}")
    public List<StatusHistoryResponseDTO> getStatusHistories(
            @PathVariable Long jobAppId,
            Authentication authentication) {
        return service.getStatusHistories(jobAppId, authentication);
    }

}

