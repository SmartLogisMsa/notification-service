package com.smartlogis.notificationservice.infrastructure.slack;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.smartlogis.notificationservice.application.MessageService;
import com.smartlogis.notificationservice.infrastructure.slack.exception.SlackException;
import com.smartlogis.notificationservice.infrastructure.slack.exception.SlackMessageCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({SlackProperties.class})
public class SlackMessageService implements MessageService {

	private final SlackProperties properties;
	private final RestClient client;

	@Override
	public String openDirectMessage(List<String> slackIds) {
		ResponseEntity<JsonNode> response = client.post()
			.uri("/conversations.open")
			.header("Authorization", "Bearer " + properties.token)
			.contentType(MediaType.APPLICATION_JSON)
			.body(Map.of("users", String.join(",", slackIds)))
			.retrieve()
			.toEntity(JsonNode.class);

		JsonNode body = response.getBody();
		if (!response.getStatusCode().is2xxSuccessful() || body.get("ok") == null || !body.get("ok").toString().equals("true")) {
			throw new SlackException(SlackMessageCode.FAIL_CREATE_CHANNEL, String.valueOf(body.get("error")));
		}

		return body.get("channel").get("id").textValue();
	}

	@Override
	public void sendMessage(String channelId, String message) {
		ResponseEntity<JsonNode> response = client.post()
			.uri("/chat.postMessage")
			.header("Authorization", "Bearer " + properties.token)
			.body(Map.of("channel", channelId, "text", message, "as_user", true))
			.contentType(MediaType.APPLICATION_JSON)
			.retrieve()
			.toEntity(JsonNode.class);

		JsonNode body = response.getBody();
		if (!response.getStatusCode().is2xxSuccessful() || body.get("ok") == null || !body.get("ok").toString().equals("true")) {
			throw new SlackException(SlackMessageCode.FAIL_SEND_MESSAGE, String.valueOf(body.get("error")));
		}
	}
}
