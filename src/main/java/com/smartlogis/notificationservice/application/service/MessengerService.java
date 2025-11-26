package com.smartlogis.notificationservice.application.service;

import java.util.List;

import com.smartlogis.notificationservice.application.service.dto.MessengerResult;

public interface MessengerService {
	MessengerResult openDirectMessage(List<String> slackIds);
	MessengerResult sendMessage(String channelId, String message);
}
