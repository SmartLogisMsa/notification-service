package com.smartlogis.notificationservice.presentation.dto;

import com.smartlogis.notificationservice.domain.NotificationStatus;
import com.smartlogis.notificationservice.domain.NotificationType;
import com.smartlogis.notificationservice.presentation.annotation.EnumValid;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationLogSearchRequest {
	@Schema(description = "알림 타입(채널, DM)", defaultValue = "direct_message")
	@EnumValid(enumClass = NotificationType.class) private String type;

	@Schema(description = "알림 상태")
	@EnumValid(enumClass = NotificationStatus.class) private String status;
}
