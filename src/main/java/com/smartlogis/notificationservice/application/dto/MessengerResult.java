package com.smartlogis.notificationservice.application.dto;

import com.smartlogis.notificationservice.domain.NotificationStatus;

public record MessengerResult(
	String channelId,
	NotificationStatus status,
	String errorMessage
) {
	public static MessengerResult of(String channelId, String status, String errorMessage) {
		return new MessengerResult(channelId, NotificationStatus.fromString(status), errorMessage);
	}
}
