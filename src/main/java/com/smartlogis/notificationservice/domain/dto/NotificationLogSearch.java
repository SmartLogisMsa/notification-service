package com.smartlogis.notificationservice.domain.dto;

import com.smartlogis.notificationservice.domain.NotificationStatus;
import com.smartlogis.notificationservice.domain.NotificationType;

public record NotificationLogSearch(
	NotificationType type,
	NotificationStatus status
) {}
