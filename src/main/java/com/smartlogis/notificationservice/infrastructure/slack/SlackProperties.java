package com.smartlogis.notificationservice.infrastructure.slack;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "slack")
public record SlackProperties (
	String token
) {}
