package com.swchoi.aispendanalyzer.domain.asset.entity;

import com.swchoi.aispendanalyzer.domain.member.entity.Member;
import com.swchoi.aispendanalyzer.domain.spending.entity.Spending;
import com.swchoi.aispendanalyzer.global.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Asset extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name; // 자산 별칭 (예: 월급 통장, 생활비 카드)

    @Column(nullable = false, unique = true) // 중복 저장을 막기 위해 unique 설정 권장
    private String accountNo;

    @Enumerated(EnumType.STRING) // Enum 이름을 문자열로 DB에 저장
    @Column(nullable = false)
    private AssetType assetType;

    private Long balance; // 현재 잔액

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    // 이 자산에서 발생한 소비 내역들 (양방향 매핑은 선택사항이나 조회 시 편리함)
    @OneToMany(mappedBy = "asset", cascade = CascadeType.ALL)
    private List<Spending> spendings = new ArrayList<>();

    @Builder
    public Asset(String name, String accountNo, AssetType assetType, Long balance, Member member) {
        this.name = name;
        this.accountNo = accountNo;
        this.assetType = assetType;
        this.balance = balance;
        this.member = member;
    }
}