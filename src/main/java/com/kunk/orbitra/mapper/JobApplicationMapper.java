package com.kunk.orbitra.mapper;

import com.kunk.orbitra.dto.JobApplicationResponseDTO;
import com.kunk.orbitra.model.JobApplication;
import org.springframework.stereotype.Component;

@Component
public class JobApplicationMapper {
    public JobApplicationResponseDTO toDTO(JobApplication j) {
        JobApplicationResponseDTO dto = new JobApplicationResponseDTO();

        dto.setId(j.getId());
        dto.setCompanyName(j.getCompanyName());
        dto.setJobRole(j.getJobRole());
        dto.setJobId(j.getJobId());
        dto.setJobType(j.getJobType());
        dto.setApplyLink(j.getApplyLink());
        dto.setCompanyProfileLink(j.getCompanyProfileLink());
        dto.setStatus(j.getStatus());
        dto.setSource(j.getSource());
        dto.setDateApplied(j.getDateApplied());
        dto.setNotes(j.getNotes());
        dto.setCreatedAt(j.getCreatedAt());
        dto.setUpdatedAt(j.getUpdatedAt());

        return dto;
    }
}
