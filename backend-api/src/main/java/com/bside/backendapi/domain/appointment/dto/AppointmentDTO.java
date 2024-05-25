package com.bside.backendapi.domain.appointment.dto;

import com.bside.backendapi.domain.appointment.entity.Appointment;
import com.bside.backendapi.domain.penalty.dto.PenaltyDTO;
import com.bside.backendapi.domain.penalty.entity.Penalty;
import com.bside.backendapi.domain.userappt.dto.UserApptDTO;
import com.bside.backendapi.domain.userappt.entity.UserAppt;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDTO {

    private Long id;
    private String title;
    private int count;
    private LocalDateTime time;
    private String place;
    private String latitude;
    private String longitude;
    private PenaltyDTO penalty;
    private List<UserApptDTO> userAppts;

    public AppointmentDTO(Long id, String title, int count, LocalDateTime time, String place, String latitude, String longitude) {
    }

    public static AppointmentDTO toDTO(Appointment appointment) {
        PenaltyDTO penaltyDTO = appointment.getPenalty() != null ? PenaltyDTO.toDTO(appointment.getPenalty()) : null;
        List<UserApptDTO> userApptDTOs = appointment.getUserAppts().stream()
                .map(UserApptDTO::toDTO)
                .collect(Collectors.toList());
        return new AppointmentDTO(
                appointment.getId(),
                appointment.getTitle(),
                appointment.getCount(),
                appointment.getTime(),
                appointment.getPlace(),
                appointment.getLatitude(),
                appointment.getLongitude(), 
                penaltyDTO,
                userApptDTOs
        );
    }
}
