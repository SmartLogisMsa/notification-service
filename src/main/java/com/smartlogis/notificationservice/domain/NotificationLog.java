package com.smartlogis.notificationservice.domain;

import com.smartlogis.common.domain.AbstractEntity;
import com.smartlogis.notificationservice.domain.dto.NotificationCreate;
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
	private MessageType type;

	@Column(nullable = false)
	private String channelId;

	@Column(columnDefinition = "TEXT")
	private String message;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private MessageStatus status;

	@Column
	private String errorMessage;

	public static NotificationLog create(NotificationCreate request) {
		validateMessageType(request.type());
		validateChannelId(request.channelId());

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

	private static void validateMessageType(MessageType type) {
		if (type == null) {
			throw new IllegalArgumentException("메세지 타입(messageType)은 빈 값일 수 없습니다.");
		}
	}

	private static void validateChannelId(String channelId) {
		if (channelId == null || channelId.isBlank()) {
			throw new IllegalArgumentException("채널 ID(channelId)는 빈 값일 수 없습니다.");
		}
	}
}
