package com.smartlogis.notificationservice.domain.exception;

import com.smartlogis.common.exception.AbstractException;
import com.smartlogis.common.exception.MessageCode;

public class NotificationException extends AbstractException {
	public NotificationException(MessageCode messageCode) {
		super(messageCode);
	}

	public NotificationException(MessageCode messageCode, Object... messageArguments) {
		super(messageCode, messageArguments);
	}

	public NotificationException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}
}