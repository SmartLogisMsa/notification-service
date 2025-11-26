package com.smartlogis.notificationservice.infrastructure.slack;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.smartlogis.notificationservice.application.MessengerService;
import com.smartlogis.notificationservice.application.dto.MessengerResult;
import com.smartlogis.notificationservice.infrastructure.slack.exception.SlackException;
import com.smartlogis.notificationservice.infrastructure.slack.exception.SlackMessageCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@EnableConfigurationProperties({SlackProperties.class})
public class SlackMessengerService implements MessengerService {

	private final SlackProperties properties;
	private final RestClient client;

	@Override
	public MessengerResult openDirectMessage(List<String> slackIds) {
		try {
			ResponseEntity<JsonNode> response = client.post()
				.uri("/conversations.open")
				.header("Authorization", "Bearer " + properties.token)
				.contentType(MediaType.APPLICATION_JSON)
				.body(Map.of("users", String.join(",", slackIds)))
				.retrieve()
				.toEntity(JsonNode.class);

			JsonNode body = response.getBody();

			if (!isSuccessful(response)) {
				return MessengerResult.of(null, "FAIL", String.valueOf(body.get("error")));
			}

			String channelId = body.get("channel").get("id").textValue();
			return MessengerResult.of(channelId, "SUCCESS", null);
		} catch (Exception e) {
			throw new SlackException(SlackMessageCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	@Override
	public MessengerResult sendMessage(String channelId, String message) {
		try {
			ResponseEntity<JsonNode> response = client.post()
				.uri("/chat.postMessage")
				.header("Authorization", "Bearer " + properties.token)
				.body(Map.of("channel", channelId, "text", message, "as_user", true))
				.contentType(MediaType.APPLICATION_JSON)
				.retrieve()
				.toEntity(JsonNode.class);

			JsonNode body = response.getBody();

			if (!isSuccessful(response)) {
				return MessengerResult.of(channelId, "FAIL", String.valueOf(body.get("error")));
			}
			return MessengerResult.of(channelId, "SUCCESS", null);
		} catch (Exception e) {
			throw new SlackException(SlackMessageCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}

	private boolean isSuccessful(ResponseEntity<JsonNode> response) {
		JsonNode body = response.getBody();
		if (!response.getStatusCode().is2xxSuccessful() ||body.get("ok") == null || !body.get("ok").toString().equals("true")) return false;

		return true;
	}
}
