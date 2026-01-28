package com.swchoi.aispendanalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AiSpendAnalyzerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AiSpendAnalyzerApplication.class, args);
    }

}
