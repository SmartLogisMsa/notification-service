package com.smartlogis.notificationservice.domain.exception;

import org.springframework.http.HttpStatus;

import com.smartlogis.common.exception.MessageCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationLogMessageCode implements MessageCode {
	NOTIFICATION_NOT_FOUND("NOTICE.NOTIFICATION_NOT_FOUND", HttpStatus.NOT_FOUND),
	INVALID_MESSAGE_STATUS("NOTICE.INVALID_MESSAGE_STATUS", HttpStatus.BAD_REQUEST),
	INVALID_MESSAGE_TYPE("NOTICE.INVALID_MESSAGE_TYPE", HttpStatus.BAD_REQUEST),
	DELETE_NOT_ALLOWED("NOTICE.DELETE_NOT_ALLOWED", HttpStatus.METHOD_NOT_ALLOWED),
	;

    private final String code;
    private final HttpStatus status;
}
