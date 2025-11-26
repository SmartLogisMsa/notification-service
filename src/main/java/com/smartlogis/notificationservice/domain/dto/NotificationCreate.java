package com.smartlogis.notificationservice.domain.dto;

import com.smartlogis.notificationservice.domain.MessageStatus;
import com.smartlogis.notificationservice.domain.MessageType;

public record NotificationCreate (
	MessageType type,
	String channelId,
	String message,
	MessageStatus status,
	String errorMessage
) {}
