package com.smartlogis.notificationservice.presentation;

import static com.smartlogis.common.presentation.ApiResponse.*;
import static org.springframework.http.ResponseEntity.*;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartlogis.common.presentation.ApiResponse;
import com.smartlogis.common.presentation.dto.PageRequest;
import com.smartlogis.common.presentation.dto.PageResponse;
import com.smartlogis.notificationservice.application.service.NotificationService;
import com.smartlogis.notificationservice.presentation.dto.NotificationLogResponse;
import com.smartlogis.notificationservice.presentation.dto.NotificationLogSearchRequest;
import com.smartlogis.notificationservice.presentation.dto.NotificationSendRequest;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class NotificationController {

	private final NotificationService notificationService;

	@Operation(summary = "알림 발송")
	@PostMapping
	public ResponseEntity<ApiResponse<Void>> send(
		@Valid @RequestBody NotificationSendRequest request
	) {
		notificationService.sendDirectMessage(request.getSlackIds(), request.getMessage());
		return ok(success());
	}

	@Operation(summary = "알림 로그 목록 조회")
	@PreAuthorize("hasRole('MASTER')")
	@GetMapping("/logs")
	public ResponseEntity<ApiResponse<PageResponse<NotificationLogResponse>>> getNotificationLogs(
		@ParameterObject NotificationLogSearchRequest search,
		@ParameterObject PageRequest page
	) {
		PageResponse<NotificationLogResponse> notificationLogs = notificationService.getNotificationLogs(search, page);
		return ok(successWithDataOnly(notificationLogs));
	}

	@Operation(summary = "알림 로그 단건 조회")
	@PreAuthorize("hasRole('MASTER')")
	@GetMapping("/logs/{logId}")
	public ResponseEntity<ApiResponse<NotificationLogResponse>> getNotificationById(
		@PathVariable Long logId
	) {
		NotificationLogResponse notificationLog = notificationService.getNotificationLogById(logId);
		return ok(successWithDataOnly(notificationLog));
	}
}
