package com.kunk.orbitra.mapper;

import com.kunk.orbitra.dto.JobApplicationResponseDTO;
import com.kunk.orbitra.dto.UserResponseDTO;
import com.kunk.orbitra.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {
    @Autowired
    private JobApplicationMapper jobMapper;

    public UserResponseDTO toDTO(User user) {
//        List<JobApplicationResponseDTO> jobs = user.getJobApplications()
//                .stream()
//                .map(jobMapper::toDTO)
//                .toList();

        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
//        dto.setJobApplications(jobs);

        return dto;
    }
}
