package com.swchoi.aispendanalyzer.domain.spending.dto;

import lombok.Getter;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;

import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionResponseDto {
    private List<TransactionDetail> resTrdtList;

    @Getter
    public static class TransactionDetail {
        private String resTrdtDay;        // 거래일자
        private String resTrdtTime;       // 거래시간
        private Long resAfterTranBalance; // 거래후 잔액
        private Long resAccountOut;       // 출금금액 -> Spending.amount 매핑용
        private String resTranDesc;       // 거래내용(가맹점) -> Spending.content 매핑용
    }
}
