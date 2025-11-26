package com.smartlogis.notificationservice.application.service;

import com.smartlogis.notificationservice.infrastructure.feignclient.dto.DeliveryDeadlineFeignRequest;

public interface AiClientService {
	String generateDeliveryDeadline(DeliveryDeadlineFeignRequest request);
}
