package com.smartlogis.notificationservice.application;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogis.common.presentation.dto.PageRequest;
import com.smartlogis.common.presentation.dto.PageResponse;
import com.smartlogis.notificationservice.application.dto.MessengerResult;
import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.NotificationStatus;
import com.smartlogis.notificationservice.domain.NotificationType;
import com.smartlogis.notificationservice.domain.dto.NotificationLogCreate;
import com.smartlogis.notificationservice.domain.service.NotificationCreateService;
import com.smartlogis.notificationservice.domain.service.NotificationQueryService;
import com.smartlogis.notificationservice.presentation.dto.NotificationLogResponse;
import com.smartlogis.notificationservice.presentation.dto.NotificationLogSearchRequest;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

	private final MessengerService messengerService;
	private final NotificationQueryService notificationQueryService;
	private final NotificationCreateService notificationCreateService;


	@Override
	@Transactional(readOnly = true)
	public NotificationLogResponse getNotificationLogById(Long id) {
		NotificationLog log = notificationQueryService.getNotificationById(id);
		return NotificationLogResponse.from(log);
	}

	@Override
	public PageResponse<NotificationLogResponse> getNotificationLogs(NotificationLogSearchRequest search, PageRequest page) {
		Page<NotificationLog> logs = notificationQueryService.getNotificationLogs(
			NotificationMapper.toDomain(search), NotificationMapper.getPageable(page)
		);
		return PageResponse.from(logs, NotificationLogResponse.class);
	}

	@Override
	public void sendDirectMessage(List<String> slackIds, String message) {
		MessengerResult channel = messengerService.openDirectMessage(slackIds);
		String channelId = channel.channelId();

		if (channel.status() == NotificationStatus.FAIL) {
			notificationCreateService.create(
				NotificationLogCreate.of(
					NotificationType.DIRECT_MESSAGE,
					channelId,
					message,
					channel.status(),
					channel.errorMessage()
				)
			);
		} else {
			MessengerResult result = messengerService.sendMessage(channelId, message);

			notificationCreateService.create(
				NotificationLogCreate.of(
					NotificationType.DIRECT_MESSAGE,
					channelId,
					message,
					result.status(),
					result.errorMessage()
				)
			);
		}
	}

}
