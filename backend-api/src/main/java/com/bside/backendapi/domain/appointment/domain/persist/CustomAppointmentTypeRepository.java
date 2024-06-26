package com.bside.backendapi.domain.appointment.domain.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomAppointmentTypeRepository extends JpaRepository<CustomAppointmentType, Long> {

    Optional<CustomAppointmentType> findByMemberIdAndTypeName(Long memberId, String typeName);

    Optional<CustomAppointmentType> findAllByMemberId(Long memberId);

}
