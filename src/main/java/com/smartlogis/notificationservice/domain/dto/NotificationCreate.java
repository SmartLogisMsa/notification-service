package com.smartlogis.notificationservice.domain.dto;

import com.smartlogis.notificationservice.domain.NotificationStatus;
import com.smartlogis.notificationservice.domain.NotificationType;

public record NotificationCreate (
	NotificationType type,
	String channelId,
	String message,
	NotificationStatus status,
	String errorMessage
) {}
