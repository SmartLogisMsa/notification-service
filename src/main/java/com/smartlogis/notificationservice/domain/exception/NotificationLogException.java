package com.smartlogis.notificationservice.domain.exception;

import com.smartlogis.common.exception.AbstractException;
import com.smartlogis.common.exception.MessageCode;

public class NotificationLogException extends AbstractException {
	public NotificationLogException(MessageCode messageCode) {
		super(messageCode);
	}

	public NotificationLogException(MessageCode messageCode, Object... messageArguments) {
		super(messageCode, messageArguments);
	}

	public NotificationLogException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}
}