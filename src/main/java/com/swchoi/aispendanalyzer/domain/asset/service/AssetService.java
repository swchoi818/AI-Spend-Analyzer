package com.swchoi.aispendanalyzer.domain.asset.service;

import com.swchoi.aispendanalyzer.domain.asset.dto.AssetRequestDto;
import com.swchoi.aispendanalyzer.domain.asset.dto.AssetResponseDto;
import com.swchoi.aispendanalyzer.domain.asset.entity.Asset;
import com.swchoi.aispendanalyzer.domain.asset.repository.AssetRepository;
import com.swchoi.aispendanalyzer.infrastructure.api.CodefAPIRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final CodefAPIRequest codefAPIRequest;
    private final AssetRepository assetRepository;

    @Transactional
    public void syncAssets(AssetRequestDto requestDto) {
        AssetResponseDto response = codefAPIRequest.getAccounts(requestDto);

        if ("CF-00000".equals(response.getResCode())) {
            // detail은 리스트 내의 AssetDetail 객체 하나를 의미함
            response.getData().getResAccountList().forEach(detail -> {
                Asset asset = Asset.builder()
                        .name(detail.getResAccountName())        // DTO의 계좌명 사용
                        .balance(detail.getResAccountBalance())  // DTO의 잔액 사용
                        .accountNo(detail.getResAccountNumber()) // DTO의 계좌번호 사용
                        // .member(member) // 실제 서비스 시에는 해당 사용자 연관관계 설정 필요
                        .build();

                assetRepository.save(asset);
            });
        }
    }
}