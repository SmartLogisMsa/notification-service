package com.smartlogis.notificationservice.domain;

import com.smartlogis.notificationservice.domain.exception.NotificationLogException;
import com.smartlogis.notificationservice.domain.exception.NotificationLogMessageCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationStatus {
	SUCCESS("SUCCESS"),
	FAIL("FAIL");

	private final String value;

	public static NotificationStatus fromString(String string) {
		try {
			return NotificationStatus.valueOf(string.toUpperCase());
		} catch (NullPointerException | IllegalArgumentException e) {
			throw new NotificationLogException(NotificationLogMessageCode.INVALID_MESSAGE_STATUS, e);
		}
	}
}
