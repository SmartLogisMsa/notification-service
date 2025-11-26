package com.smartlogis.notificationservice.domain;

import com.smartlogis.notificationservice.domain.exception.NotificationLogException;
import com.smartlogis.notificationservice.domain.exception.NotificationLogMessageCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageStatus {
	SUCCESS("SUCCESS"),
	FAIL("FAIL");

	private final String value;

	public static MessageStatus fromString(String string) {
		try {
			return MessageStatus.valueOf(string.toUpperCase());
		} catch (NullPointerException | IllegalArgumentException e) {
			throw new NotificationLogException(NotificationLogMessageCode.INVALID_MESSAGE_STATUS, e);
		}
	}
}
