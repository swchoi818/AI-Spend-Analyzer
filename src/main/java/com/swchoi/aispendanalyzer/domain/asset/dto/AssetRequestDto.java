package com.swchoi.aispendanalyzer.domain.asset.dto;

import com.swchoi.aispendanalyzer.global.common.dto.CodefBaseRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class AssetRequestDto extends CodefBaseRequest {
    // 추가적인 조회 옵션이 필요할 경우 여기에 필드 추가

    @Builder
    public AssetRequestDto(String connectedId, String organization) {
        setConnectedId(connectedId);
        setOrganization(organization);
    }
}

