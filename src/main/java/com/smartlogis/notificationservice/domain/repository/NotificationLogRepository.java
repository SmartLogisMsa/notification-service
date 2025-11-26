package com.smartlogis.notificationservice.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.Repository;

import com.smartlogis.notificationservice.domain.NotificationLog;

public interface NotificationLogRepository extends Repository<NotificationLog, Long>, NotificationRepositoryCustom {
	NotificationLog save(NotificationLog notificationLog);
	Optional<NotificationLog> findById(Long id);
}
