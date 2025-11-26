package com.smartlogis.notificationservice.application.event.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record NotifyDeliveryAssignedEvent (
	UUID orderId,
	Orderer orderer,
	List<Product> products,
	LocalDateTime orderDate,
	String orderMemo,
	String startHub,
	List<String> stopoverHub,
	String arrivalHub,
	String address,
	Double estimateTime,
	Staff staff
) {
	public record Orderer(
		String name,
		String email,
		String slackId
	) {}

	public record Staff(
		String name,
		String email,
		String slackId
	) {}

	public record Product(
		UUID id,
		String name,
		int quantity
	) {}
}

