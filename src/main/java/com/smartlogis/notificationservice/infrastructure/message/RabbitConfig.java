package com.smartlogis.notificationservice.infrastructure.message;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(RabbitProperties.class)
public class RabbitConfig {

	private final RabbitProperties properties;

	public static final String DELIVERY_CREATED = "delivery-created";

	@Bean
	public Queue deliveryCreatedQueue() {
		return new Queue(properties.get(DELIVERY_CREATED).queue(), true);
	}

	@Bean
	public TopicExchange deliveryCreatedExchange() {
		return new TopicExchange(properties.get(DELIVERY_CREATED).exchange());
	}

	@Bean
	public Binding deliveryCreatedBinding() {
		return BindingBuilder
			.bind(deliveryCreatedQueue())
			.to(deliveryCreatedExchange())
			.with(properties.get(DELIVERY_CREATED).routingKey());
	}

	@Bean
	public MessageConverter messageConverter(){
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
		ConnectionFactory connectionFactory,
		MessageConverter messageConverter
	) {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory);
		factory.setMessageConverter(messageConverter);
		return factory;
	}
}
