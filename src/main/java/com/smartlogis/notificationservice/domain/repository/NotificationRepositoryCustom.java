package com.smartlogis.notificationservice.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationSearch;

public interface NotificationRepositoryCustom {
	Page<NotificationLog> getNotifications(NotificationSearch search, Pageable pageable);
}
