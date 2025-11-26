package com.smartlogis.notificationservice.presentation.dto;

import java.time.LocalDateTime;

import com.smartlogis.notificationservice.domain.NotificationLog;

public record NotificationLogResponse (
	Long id,
	String type,
	String channelId,
	String message,
	String status,
	String errorMessage,
	LocalDateTime createdAt,
	String createdBy
) {
	public static NotificationLogResponse from(NotificationLog entity) {
		return new NotificationLogResponse(
			entity.getId(),
			entity.getType().getValue(),
			entity.getChannelId(),
			entity.getMessage(),
			entity.getStatus().getValue(),
			entity.getErrorMessage(),
			entity.getCreatedAt(),
			entity.getCreatedBy()
		);
	}
}
