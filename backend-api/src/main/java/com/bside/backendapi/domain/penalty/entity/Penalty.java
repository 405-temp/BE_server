package com.bside.backendapi.domain.penalty.entity;

import com.bside.backendapi.domain.appointment.entity.Appointment;
import com.bside.backendapi.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor() //access = AccessLevel.PROTECTED
@AllArgsConstructor() //access = AccessLevel.PROTECTED
public class Penalty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "penalty_id")
    private int id;

    private String content;

    @Enumerated(EnumType.STRING)
    private PenaltyType penaltyType;

    private int fine;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "penalty", fetch = FetchType.LAZY)
    private Appointment appointment;

    @OneToMany(mappedBy = "penalty", fetch = FetchType.LAZY)
    private List<ReceivedPenalty> receivedPenalties = new ArrayList<>();

}