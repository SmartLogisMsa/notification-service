package com.smartlogis.notificationservice.domain;

import com.smartlogis.notificationservice.domain.exception.NotificationLogException;
import com.smartlogis.notificationservice.domain.exception.NotificationLogMessageCode;

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
			throw new NotificationLogException(NotificationLogMessageCode.INVALID_MESSAGE_TYPE, e);
		}
	}
}
