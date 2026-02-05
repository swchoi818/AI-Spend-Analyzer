package com.swchoi.aispendanalyzer.infrastructure.api;

import com.swchoi.aispendanalyzer.domain.asset.dto.AssetRequestDto;
import com.swchoi.aispendanalyzer.domain.asset.dto.AssetResponseDto;
import com.swchoi.aispendanalyzer.domain.spending.dto.TransactionRequestDto;
import com.swchoi.aispendanalyzer.domain.spending.dto.TransactionResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CodefAPIRequest {

    private final WebClient webClient; // Config에서 Bean으로 설정한 WebClient 사용

    // CODEF 설정 정보 (보통 application.yml에서 가져옴)
    @Value("${codef.client-id}")
    private String clientId;

    @Value("${codef.client-secret}")
    private String clientSecret;

    public AssetResponseDto getAccounts(AssetRequestDto requestDto) {
        return webClient.post()
                .uri("/v1/kr/bank/accounts")
                .header("Authorization", "Bearer " + getAccessToken())
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(AssetResponseDto.class)
                .block();
    }

    public TransactionResponseDto getTransactions(TransactionRequestDto requestDto) {
        return webClient.post()
                .uri("/v1/kr/bank/transaction-list")
                .header("Authorization", "Bearer " + getAccessToken())
                .bodyValue(requestDto)
                .retrieve()
                .bodyToMono(TransactionResponseDto.class)
                .block();
    }

    private static final String TOKEN_URL = "https://oauth.codef.io/oauth/token";

    private String cachedToken;

    public String getAccessToken() {
        if (cachedToken != null) {
            return cachedToken; // 이미 있으면 재사용
        }
        // 1. Client ID와 Secret을 Base64 인코딩 (Basic Auth)
        String auth = clientId + ":" + clientSecret;
        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());

        Mono<Map> response = webClient.post()
                .uri(TOKEN_URL)
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue("grant_type=client_credentials&scope=read")
                .retrieve()
                .bodyToMono(Map.class);

        Map<String, Object> result = response.block();

        if (result != null && result.containsKey("access_token")) {
            this.cachedToken = (String) result.get("access_token");
            return this.cachedToken;
        }
        throw new RuntimeException("CODEF 토큰 발급에 실패했습니다.");
    }
}
