package com.swchoi.aispendanalyzer.global.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodefBaseRequest {
    private String connectedId;    // 사용자의 연동 계정 ID
    private String organization;   // 기관 코드 (예: 0004 - KB국민은행)
}