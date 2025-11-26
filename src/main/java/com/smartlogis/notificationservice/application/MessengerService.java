package com.smartlogis.notificationservice.application;

import java.util.List;

import com.smartlogis.notificationservice.application.dto.MessengerResult;

public interface MessengerService {
	MessengerResult openDirectMessage(List<String> slackIds);
	MessengerResult sendMessage(String channelId, String message);
}
