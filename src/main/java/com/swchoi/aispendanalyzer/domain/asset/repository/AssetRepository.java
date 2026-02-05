package com.swchoi.aispendanalyzer.domain.asset.repository;

import com.swchoi.aispendanalyzer.domain.asset.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}