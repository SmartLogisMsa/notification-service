package com.smartlogis.notificationservice.domain.dto;

import com.smartlogis.notificationservice.domain.MessageStatus;
import com.smartlogis.notificationservice.domain.MessageType;

public record NotificationSearch(
	MessageType type,
	MessageStatus status
) {}
