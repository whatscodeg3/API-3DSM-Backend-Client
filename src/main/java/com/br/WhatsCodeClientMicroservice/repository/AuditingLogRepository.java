package com.br.WhatsCodeClientMicroservice.repository;

import com.br.WhatsCodeClientMicroservice.models.AuditingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditingLogRepository extends JpaRepository<AuditingLog, Long> {
}
