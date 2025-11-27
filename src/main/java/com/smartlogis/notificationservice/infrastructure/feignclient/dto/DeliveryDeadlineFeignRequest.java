package com.smartlogis.notificationservice.infrastructure.feignclient.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import lombok.Builder;

@Builder
public record DeliveryDeadlineFeignRequest(
	String model,
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
		String email
	) {}

	public record Staff(
		String name,
		String email
	) {}

	public record Product(
		String name,
		int quantity
	) {}
}
