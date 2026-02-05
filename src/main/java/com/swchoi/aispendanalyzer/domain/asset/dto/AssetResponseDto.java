package com.swchoi.aispendanalyzer.domain.asset.dto;

import lombok.Getter;
import tools.jackson.databind.PropertyNamingStrategies;
import tools.jackson.databind.annotation.JsonNaming;
import java.util.List;

@Getter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class AssetResponseDto {
    private String resCode;
    private String resMsg;
    private AssetData data;

    @Getter
    public static class AssetData {
        // CODEF의 계좌 목록 리스트 필드명에 맞춰 정의
        private List<AssetDetail> resAccountList;
    }

    @Getter
    public static class AssetDetail {
        private String resAccountName;    // 계좌명 -> Asset.name 매핑용
        private Long resAccountBalance;   // 현재잔액 -> Asset.balance 매핑용
        private String resAccountType;    // 계좌종류 -> Asset.assetType 매핑용
        private String resAccountNumber;  // 계좌번호
    }
}
