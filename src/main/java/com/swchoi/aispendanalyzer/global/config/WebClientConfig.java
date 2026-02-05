package com.swchoi.aispendanalyzer.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration // 1. 설정 클래스임을 명시
public class WebClientConfig {

    @Bean // 2. 스프링이 관리하는 Bean으로 등록
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://api.codef.io") // 공통 Base URL 설정
                .build();
    }
}
