package com.smartlogis.notificationservice.infrastructure.slack.exception;

import org.springframework.http.HttpStatus;

import com.smartlogis.common.exception.MessageCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SlackMessageCode implements MessageCode {
	INTERNAL_SERVER_ERROR("SLACK.INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR)
	;

    private final String code;
    private final HttpStatus status;
}
