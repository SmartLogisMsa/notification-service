package com.smartlogis.notificationservice.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationLogSearch;

public interface NotificationQueryService {
	NotificationLog getNotificationById(Long id);
	Page<NotificationLog> getNotificationLogs(NotificationLogSearch search, Pageable pageable);
}
