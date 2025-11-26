package com.smartlogis.notificationservice.domain;

import com.smartlogis.common.domain.AbstractEntity;
import com.smartlogis.notificationservice.domain.dto.NotificationLogCreate;
import com.smartlogis.notificationservice.domain.exception.NotificationLogException;
import com.smartlogis.notificationservice.domain.exception.NotificationLogMessageCode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "p_notification_log")
@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NotificationLog extends AbstractEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private NotificationType type;

	@Column(nullable = false)
	private String channelId;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationStatus status;

	@Column
	private String errorMessage;

	public static NotificationLog create(NotificationLogCreate request) {
		validateNotificationType(request.type());
		validateChannelId(request.channelId());
		validateNotificationStatus(request.status());

		NotificationLog log = new NotificationLog();

		log.type = request.type();
		log.channelId = request.channelId();
		log.message = request.message();
		log.status = request.status();
		log.errorMessage = request.errorMessage();

		return log;
	}

	public void delete() {
		throw new NotificationLogException(NotificationLogMessageCode.DELETE_NOT_ALLOWED);
	}

	private static void validateNotificationType(NotificationType type) {
		if (type == null) {
			throw new IllegalArgumentException("알림 타입(notificationType)은 비어 있을 수 없습니다.");
		}
	}

	private static void validateChannelId(String channelId) {
		if (channelId == null || channelId.isBlank()) {
			throw new IllegalArgumentException("채널 ID(channelId)는 비어 있을 수 없습니다.");
		}
	}

	private static void validateNotificationStatus(NotificationStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("알림 상태(notificationStatus)는 비어 있을 수 없습니다.");
		}
	}
}
