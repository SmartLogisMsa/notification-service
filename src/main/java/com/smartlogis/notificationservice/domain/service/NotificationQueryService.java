package com.smartlogis.notificationservice.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationSearch;

public interface NotificationQueryService {
	NotificationLog getById(Long id);
	Page<NotificationLog> getNotificationLogs(NotificationSearch search, Pageable pageable);
}
