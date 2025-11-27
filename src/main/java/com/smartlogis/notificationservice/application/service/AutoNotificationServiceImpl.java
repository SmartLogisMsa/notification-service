package com.smartlogis.notificationservice.application.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogis.notificationservice.infrastructure.feignclient.dto.DeliveryDeadlineFeignRequest;
import com.smartlogis.notificationservice.application.service.dto.SendDeliveryDeadlineCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AutoNotificationServiceImpl implements AutoNotificationService {

	private final AiClientService aiClientService;
	private final NotificationService notificationService;

	@Override
	public void sendDeliveryDeadline(SendDeliveryDeadlineCommand command) {
		DeliveryDeadlineFeignRequest request = toDeliveryDeadlineFeignRequest(command);
		String message = aiClientService.generateDeliveryDeadline(request);

		List<String> slackIds = List.of(command.staff().slackId());
		notificationService.sendDirectMessage(slackIds, message);
	}

	private DeliveryDeadlineFeignRequest toDeliveryDeadlineFeignRequest(SendDeliveryDeadlineCommand command) {
		return DeliveryDeadlineFeignRequest.builder()
			.orderId(command.orderId())
			.orderer(new DeliveryDeadlineFeignRequest.Orderer(
				command.orderer().name(),
				command.orderer().email()
			))
			.products(command.products().stream()
				.map(p -> new DeliveryDeadlineFeignRequest.Product(p.name(), p.quantity()))
				.toList()
			)
			.orderDate(command.orderDate())
			.startHub(command.startHub())
			.stopoverHub(command.stopoverHub())
			.arrivalHub(command.arrivalHub())
			.address(command.address())
			.estimateTime(command.estimateTime())
			.staff(new DeliveryDeadlineFeignRequest.Staff(
				command.staff().name(),
				command.staff().email()
			))
			.build();
	}
}
