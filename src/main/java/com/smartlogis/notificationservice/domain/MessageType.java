package com.smartlogis.notificationservice.domain;

import com.smartlogis.notificationservice.domain.exception.NotificationException;
import com.smartlogis.notificationservice.domain.exception.NotificationMessageCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MessageType {
	CHANNEL("channel"), DIRECT_MESSAGE("direct_message");

	private final String value;

	public static MessageType fromString(String string) {
		try {
			return MessageType.valueOf(string.toUpperCase());
		} catch (NullPointerException | IllegalArgumentException e) {
			throw new NotificationException(NotificationMessageCode.INVALID_MESSAGE_TYPE, e);
		}
	}
}
