package com.smartlogis.notificationservice.infrastructure.feignclient.exception;

import com.smartlogis.common.exception.AbstractException;
import com.smartlogis.common.exception.MessageCode;

public class FeignException extends AbstractException {
	public FeignException(MessageCode messageCode) {
		super(messageCode);
	}

	public FeignException(MessageCode messageCode, String message) {
		super(messageCode, message);
	}
}