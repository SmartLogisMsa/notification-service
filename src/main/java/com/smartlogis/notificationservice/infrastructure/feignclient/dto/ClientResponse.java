package com.smartlogis.notificationservice.infrastructure.feignclient.dto;

public record ClientResponse<T> (
	boolean success,
	String messageCode,
	String message,
	T data
) {}
