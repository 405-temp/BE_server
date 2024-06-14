package com.bside.backendapi.domain.penalty.dto.response;

import com.bside.backendapi.domain.penalty.domain.vo.PenaltyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PenaltyResponse{

    private String receiver;
    private String content;
    private PenaltyType penaltyType;
    private int fine;

}