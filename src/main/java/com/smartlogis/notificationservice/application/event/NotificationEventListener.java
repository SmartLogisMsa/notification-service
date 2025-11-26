package com.smartlogis.notificationservice.application.event;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogis.notificationservice.application.event.dto.NotifyDeliveryAssignedEvent;
import com.smartlogis.notificationservice.application.service.AutoNotificationService;
import com.smartlogis.notificationservice.application.service.dto.SendDeliveryDeadlineCommand;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotificationEventListener {

	private final AutoNotificationService autoNotificationService;

	@Transactional
	@RabbitListener(queues = "#{@deliveryCreatedQueue.name}")
	public void handleNotifyDeliveryAssignedEvent(NotifyDeliveryAssignedEvent event) {
		try {
			SendDeliveryDeadlineCommand command = toSendDeliveryDeadlineCommand(event);
			autoNotificationService.sendDeliveryDeadline(command);
		} catch (Exception e) {
			log.error("[NotifyDeliveryAssignedEvent] 처리 실패", e);
		}
	}

	private SendDeliveryDeadlineCommand toSendDeliveryDeadlineCommand(NotifyDeliveryAssignedEvent event) {
		return SendDeliveryDeadlineCommand.builder()
			.orderId(event.orderId())
			.orderer(new SendDeliveryDeadlineCommand.Orderer(
				event.orderer().name(),
				event.orderer().email(),
				event.orderer().slackId()
			))
			.products(event.products().stream()
				.map(p -> new SendDeliveryDeadlineCommand.Product(p.id(), p.name(), p.quantity()))
				.toList()
			)
			.orderDate(event.orderDate())
			.startHub(event.startHub())
			.stopoverHub(event.stopoverHub())
			.arrivalHub(event.arrivalHub())
			.address(event.address())
			.estimateTime(event.estimateTime())
			.staff(new SendDeliveryDeadlineCommand.Staff(
				event.staff().name(),
				event.staff().email(),
				event.staff().slackId()
			))
			.build();
	}
}
