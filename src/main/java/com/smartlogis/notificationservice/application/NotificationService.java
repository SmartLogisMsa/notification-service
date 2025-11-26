package com.smartlogis.notificationservice.application;

import java.util.List;

import com.smartlogis.common.presentation.dto.PageRequest;
import com.smartlogis.common.presentation.dto.PageResponse;
import com.smartlogis.notificationservice.presentation.dto.NotificationLogResponse;
import com.smartlogis.notificationservice.presentation.dto.NotificationLogSearchRequest;

public interface NotificationService {
	NotificationLogResponse getNotificationLogById(Long id);
	PageResponse<NotificationLogResponse> getNotificationLogs(NotificationLogSearchRequest search, PageRequest page);
	void sendDirectMessage(List<String> slackIds, String message);
}
