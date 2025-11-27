package com.smartlogis.notificationservice.infrastructure.slack.exception;

import org.springframework.http.HttpStatus;

import com.smartlogis.common.exception.MessageCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SlackMessageCode implements MessageCode {
	FAIL_CREATE_CHANNEL("SLACK.FAIL_CREATE_CHANNEL", HttpStatus.INTERNAL_SERVER_ERROR),
	FAIL_SEND_MESSAGE("SLACK.FAIL_SEND_MESSAGE", HttpStatus.INTERNAL_SERVER_ERROR),
	;

    private final String code;
    private final HttpStatus status;
}
