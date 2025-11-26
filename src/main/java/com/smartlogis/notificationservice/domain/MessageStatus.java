package com.smartlogis.notificationservice.domain;

import com.smartlogis.notificationservice.domain.exception.NotificationException;
import com.smartlogis.notificationservice.domain.exception.NotificationMessageCode;

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
			throw new NotificationException(NotificationMessageCode.INVALID_MESSAGE_STATUS, e);
		}
	}
}
