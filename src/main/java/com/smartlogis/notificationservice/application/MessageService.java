package com.smartlogis.notificationservice.application;

import java.util.List;

public interface MessageService {
	String openDirectMessage(List<String> slackIds);
	void sendMessage(String channelId, String message);
}
