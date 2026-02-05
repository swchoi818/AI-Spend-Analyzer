package com.swchoi.aispendanalyzer.domain.spending.dto;

import com.swchoi.aispendanalyzer.global.common.dto.CodefBaseRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TransactionRequestDto extends CodefBaseRequest {
    private String startDate;   // 조회 시작일 (YYYYMMDD)
    private String endDate;     // 조회 종료일 (YYYYMMDD)
    private String accountNo;   // 특정 계좌의 내역을 볼 때 사용
    private String orderBy;     // 정렬 순서 (0: 역순, 1: 순차)

    @Builder
    public TransactionRequestDto(String connectedId, String organization, String startDate, String endDate, String accountNo) {
        setConnectedId(connectedId);
        setOrganization(organization);
        this.startDate = startDate;
        this.endDate = endDate;
        this.accountNo = accountNo;
        this.orderBy = "0"; // 최신순 기본값
    }
}
