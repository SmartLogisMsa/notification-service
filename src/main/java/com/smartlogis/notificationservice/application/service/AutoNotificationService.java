package com.smartlogis.notificationservice.application.service;

import com.smartlogis.notificationservice.application.service.dto.SendDeliveryDeadlineCommand;

public interface AutoNotificationService {
	void sendDeliveryDeadline(SendDeliveryDeadlineCommand command);
}
