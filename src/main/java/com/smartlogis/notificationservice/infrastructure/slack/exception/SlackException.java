package com.smartlogis.notificationservice.infrastructure.slack.exception;

import com.smartlogis.common.exception.AbstractException;
import com.smartlogis.common.exception.MessageCode;

public class SlackException extends AbstractException {
	public SlackException(MessageCode messageCode) {
		super(messageCode);
	}

	public SlackException(MessageCode messageCode, Object... messageArguments) {
		super(messageCode, messageArguments);
	}

	public SlackException(MessageCode messageCode, Throwable cause) {
		super(messageCode, cause);
	}
}