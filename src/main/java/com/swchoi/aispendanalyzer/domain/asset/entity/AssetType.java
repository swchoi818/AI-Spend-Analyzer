package com.swchoi.aispendanalyzer.domain.asset.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AssetType {
    BANK("은행 계좌"),
    CARD("신용/체크카드"),
    CASH("현금"),
    INVESTMENT("투자/주식");

    private final String description;
}