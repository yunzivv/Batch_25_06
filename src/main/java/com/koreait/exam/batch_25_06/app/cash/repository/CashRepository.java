package com.koreait.exam.batch_25_06.app.cash.repository;

import com.koreait.exam.batch_25_06.app.cash.entity.CashLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CashRepository extends JpaRepository<CashLog, Long> {
}