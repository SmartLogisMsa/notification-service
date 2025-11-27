package com.smartlogis.notificationservice.infrastructure.feignclient.dto;

public record AiGenerateFeignResponse(
	String prompt,
	String fullPrompt,
	String response,
	String model,
	String status,
	String errorMessage
) {}