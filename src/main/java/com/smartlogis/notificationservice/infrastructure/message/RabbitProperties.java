package com.smartlogis.notificationservice.infrastructure.message;

import java.util.Map;
import java.util.Optional;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "rabbit")
public record RabbitProperties (
	Map<String, BindingProperties> bindings
) {
	public record BindingProperties (
		String queue,
		String exchange,
		String routingKey
	) {}

	public RabbitProperties.BindingProperties get(String name) {
		return Optional.ofNullable(bindings.get(name))
			.orElseThrow(() -> new IllegalArgumentException("Binding not found: " + name));
	}
}
