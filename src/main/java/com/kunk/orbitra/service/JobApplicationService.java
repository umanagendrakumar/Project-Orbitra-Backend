package com.kunk.orbitra.service;

import com.kunk.orbitra.dto.CreateJobApplicationDTO;
import com.kunk.orbitra.dto.JobApplicationResponseDTO;
import com.kunk.orbitra.dto.StatusHistoryResponseDTO;
import com.kunk.orbitra.dto.UpdateJobApplicationDTO;
import com.kunk.orbitra.enums.ApplicationStatus;
import com.kunk.orbitra.enums.JobType;
import com.kunk.orbitra.mapper.JobApplicationMapper;
import com.kunk.orbitra.mapper.StatusHistoryMapper;
import com.kunk.orbitra.model.JobApplication;
import com.kunk.orbitra.model.StatusHistory;
import com.kunk.orbitra.model.User;
import com.kunk.orbitra.repo.JobApplicationRepo;
import com.kunk.orbitra.repo.StatusHistoryRepo;
import com.kunk.orbitra.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class JobApplicationService {
    @Autowired
    private JobApplicationRepo repo;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private StatusHistoryRepo statusHistoryRepo;
    @Autowired
    private JobApplicationMapper mapper;
    @Autowired
    private StatusHistoryMapper statusHistoryMapper;

    public void addJobApp(CreateJobApplicationDTO dto, Authentication authentication) {
        JobApplication jobApplication = new JobApplication();

        jobApplication.setCompanyName(dto.getCompanyName());
        jobApplication.setJobRole(dto.getJobRole());
        jobApplication.setJobId(dto.getJobId());
        jobApplication.setJobType(dto.getJobType());
        jobApplication.setApplyLink(dto.getApplyLink());
        jobApplication.setCompanyProfileLink(dto.getCompanyProfileLink());
        jobApplication.setStatus(dto.getStatus());
        jobApplication.setSource(dto.getSource());
        jobApplication.setDateApplied(dto.getDateApplied());
        jobApplication.setNotes(dto.getNotes());

        String email = authentication.getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        jobApplication.setUser(user);

        repo.save(jobApplication);
    }
    public JobApplicationResponseDTO updateJobApp(Long jobAppId, UpdateJobApplicationDTO dto, Authentication authentication) {
        String email = authentication.getName();

        JobApplication job = repo.findByIdAndUserEmail(jobAppId, email)
                .orElseThrow(() -> new RuntimeException("Job Not Found or not yours"));

        if (dto.getCompanyName() != null) job.setCompanyName(dto.getCompanyName());
        if (dto.getJobRole() != null) job.setJobRole(dto.getJobRole());
        if (dto.getJobId() != null) job.setJobId(dto.getJobId());
        if (dto.getJobType() != null) job.setJobType(dto.getJobType());
        if (dto.getApplyLink() != null) job.setApplyLink(dto.getApplyLink());
        if (dto.getCompanyProfileLink() != null) job.setCompanyProfileLink(dto.getCompanyProfileLink());
        if (dto.getStatus() != null && !job.getStatus().equals(dto.getStatus())) {
            StatusHistory statusHistory = new StatusHistory(
                    job.getStatus(),
                    dto.getStatus()
            );
            statusHistory.setJobApplication(job);
            statusHistoryRepo.save(statusHistory);

            job.setStatus(dto.getStatus());
        }
        if (dto.getSource() != null) job.setSource(dto.getSource());
        if (dto.getDateApplied() != null) job.setDateApplied(dto.getDateApplied());
        if (dto.getNotes() != null) job.setNotes(dto.getNotes());

        return mapper.toDTO(repo.save(job));
    }
    public void deleteJobApp(Long jobAppId, Authentication authentication) {
        String email = authentication.getName();

        JobApplication job = repo.findByIdAndUserEmail(jobAppId, email)
                .orElseThrow(() -> new RuntimeException("JobApplication Not Found or not yours"));

        repo.delete(job);
    }
    public List<StatusHistoryResponseDTO> getStatusHistories(Long jobAppId, Authentication authentication) {
        String email = authentication.getName();

        List<StatusHistory> histories = statusHistoryRepo
                .findByJobApplicationIdAndJobApplicationUserEmailOrderByChangedAtDesc(jobAppId, email);

        return histories
                .stream()
                .map(sh -> statusHistoryMapper.toDTO(sh))
                .toList();
    }
    public List<JobApplicationResponseDTO> getAllOrSearchJobApps(
            String query,
            JobType jobType,
            ApplicationStatus status,
            Sort.Direction direction,
            Authentication authentication) {

        String email = authentication.getName();

        if (query == null || query.trim().isEmpty())
            query = "";

        Sort sortBy = Sort.by(direction, "id");

        List<JobApplication> jobs = repo.searchJobApplications(email, query, jobType, status, sortBy);

        return jobs
                .stream()
                .map(j -> mapper.toDTO(j))
                .toList();
    }
}