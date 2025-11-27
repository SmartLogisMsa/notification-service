package com.smartlogis.notificationservice.domain.dto;

import java.util.List;

import com.smartlogis.notificationservice.domain.NotificationStatus;
import com.smartlogis.notificationservice.domain.NotificationType;

public record NotificationLogCreate(
	NotificationType type,
	String channelId,
	List<String> slackIds,
	String message,
	NotificationStatus status,
	String errorMessage
) {
	public static NotificationLogCreate of(NotificationType type, String channelId, List<String> slackIds, String message, NotificationStatus status, String errorMessage) {
		return new NotificationLogCreate(type, channelId, slackIds, message, status, errorMessage);
	}
}
