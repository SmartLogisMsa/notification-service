package com.smartlogis.notificationservice.domain;

import com.smartlogis.notificationservice.domain.exception.NotificationLogException;
import com.smartlogis.notificationservice.domain.exception.NotificationLogMessageCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationType {
	CHANNEL("channel"), DIRECT_MESSAGE("direct_message");

	private final String value;

	public static NotificationType fromString(String string) {
		try {
			return NotificationType.valueOf(string.toUpperCase());
		} catch (NullPointerException | IllegalArgumentException e) {
			throw new NotificationLogException(NotificationLogMessageCode.INVALID_MESSAGE_TYPE, e);
		}
	}
}
