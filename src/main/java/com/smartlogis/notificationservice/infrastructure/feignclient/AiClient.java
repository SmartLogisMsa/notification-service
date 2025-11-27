package com.smartlogis.notificationservice.infrastructure.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.smartlogis.notificationservice.infrastructure.feignclient.dto.AiGenerateFeignResponse;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.ClientResponse;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.DeliveryDeadlineFeignRequest;

@FeignClient("ai-service")
public interface AiClient {
	@PostMapping("/delivery-deadline")
	ClientResponse<AiGenerateFeignResponse> generateDeliveryDeadline(
		@RequestBody DeliveryDeadlineFeignRequest request
	);
}