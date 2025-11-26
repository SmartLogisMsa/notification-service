package com.smartlogis.notificationservice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationLogSearch;

public interface NotificationLogRepositoryCustom {
	Page<NotificationLog> getNotifications(NotificationLogSearch search, Pageable pageable);
}
