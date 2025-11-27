package com.smartlogis.notificationservice.infrastructure.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smartlogis.common.presentation.ApiResponse;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.DeliveryDeadlineFeignRequest;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.AiGenerateFeignResponse;

@FeignClient("ai-service")
public interface AiClient {
	@PostMapping("/delivery-deadline")
	ResponseEntity<ApiResponse<AiGenerateFeignResponse>> generateDeliveryDeadline(
		@RequestBody DeliveryDeadlineFeignRequest request
	);
}