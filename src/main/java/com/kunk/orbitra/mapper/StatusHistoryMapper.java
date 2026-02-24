package com.kunk.orbitra.mapper;

import com.kunk.orbitra.dto.StatusHistoryResponseDTO;
import com.kunk.orbitra.model.StatusHistory;
import org.springframework.stereotype.Component;

@Component
public class StatusHistoryMapper {
    public StatusHistoryResponseDTO toDTO(StatusHistory sh) {
        StatusHistoryResponseDTO dto = new StatusHistoryResponseDTO();

        dto.setId(sh.getId());
        dto.setNewStatus(sh.getNewStatus());
        dto.setOldStatus(sh.getOldStatus());
        dto.setChangedAt(sh.getChangedAt());

        return dto;
    }
}
