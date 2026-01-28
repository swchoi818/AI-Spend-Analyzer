package com.swchoi.aispendanalyzer.domain.spending.entity;

import com.swchoi.aispendanalyzer.domain.member.entity.Member;
import com.swchoi.aispendanalyzer.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Spending extends BaseEntity { // 상속 추가

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long amount; // 소비 금액

    @Column(nullable = false)
    private String category; // 식비, 쇼핑 등

    private String content; // 가맹점명 (예: 스타벅스)

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 권장
    @JoinColumn(name = "member_id")
    private Member member; // "누가" 썼는지 연결
}
