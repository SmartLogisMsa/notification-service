package com.smartlogis.notificationservice.domain;

import java.util.Arrays;
import java.util.List;

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

	@Column
	private String channelId;

	@Column
	@Getter(AccessLevel.NONE)
	private String slackIds;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private NotificationStatus status;

	@Column
	private String errorMessage;

	public static NotificationLog create(NotificationLogCreate request) {
		validateNotificationType(request.type());
		validateNotificationStatus(request.status());

		NotificationLog notificationLog = new NotificationLog();

		notificationLog.type = request.type();
		notificationLog.channelId = request.channelId();
		notificationLog.message = request.message();
		notificationLog.status = request.status();
		notificationLog.errorMessage = request.errorMessage();

		setSlackIds(notificationLog, request.slackIds());

		return notificationLog;
	}

	public List<String> getSlackIds() {
		if (this.slackIds == null || this.slackIds.isBlank()) return List.of();
		return Arrays.stream(this.slackIds.split(",")).map(String::trim).toList();
	}

	private static void setSlackIds(NotificationLog notificationLog, List<String> slackIds) {
		notificationLog.slackIds = String.join(",", slackIds);
	}

	private static void validateNotificationType(NotificationType type) {
		if (type == null) {
			throw new IllegalArgumentException("알림 타입(notificationType)은 비어 있을 수 없습니다.");
		}
	}

	private static void validateNotificationStatus(NotificationStatus status) {
		if (status == null) {
			throw new IllegalArgumentException("알림 상태(notificationStatus)는 비어 있을 수 없습니다.");
		}
	}

	public void delete() {
		throw new NotificationLogException(NotificationLogMessageCode.DELETE_NOT_ALLOWED);
	}
}
