package com.bside.backendapi.domain.penalty.service;

import com.bside.backendapi.domain.appointment.entity.Appointment;
import com.bside.backendapi.domain.appointment.repository.AppointmentRepository;
import com.bside.backendapi.domain.penalty.entity.Penalty;
import com.bside.backendapi.domain.penalty.entity.PenaltyType;
import com.bside.backendapi.domain.penalty.repository.PenaltyRepository;
import com.bside.backendapi.domain.user.entity.User;
import com.bside.backendapi.domain.user.repository.UserRepository;
import com.bside.backendapi.domain.userappt.entity.UserAppt;
import com.bside.backendapi.domain.userappt.repository.UserApptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PenaltyService {

    private final UserRepository userRepository;
    private final UserApptRepository userApptRepository;
    private final PenaltyRepository penaltyRepository;
    private final AppointmentRepository appointmentRepository;

    public void createPenalty(long uaid, PenaltyType penaltyType, String content, int fine) {
        UserAppt userAppts = userApptRepository.findUserApptById(uaid);
        User user = userRepository.findByUserId(userAppts.getUser().getId());
        Appointment appointment = appointmentRepository.findAppointmentByUserId(user.getId());

        if (appointment != null) {

            Penalty penalty = Penalty.builder()
                    .user(user)
                    .appointment(appointment)
                    .penaltyType(penaltyType)
                    .content(content)
                    .fine(fine)
                    .build();

            // 패널티 타입에 따라 초기화
            if (penaltyType == PenaltyType.FINE) {
                penalty.toBuilder().content(null);
            } else {
                penalty.toBuilder().fine(0);
            }

            // 패널티 저장
            penaltyRepository.save(penalty);

            // 약속에 생성한 패널티 부여
            appointment.toBuilder().penalty(penalty).build();

            // 약속 저장
            appointmentRepository.save(appointment);

        } else {
            // 예외 처리 로직 추가
            throw new RuntimeException("Appointment not found");
        }
    }
}