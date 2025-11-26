package com.smartlogis.notificationservice.presentation.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NotificationSendRequest {
	@Schema(description = "수신자", minLength = 1)
	@Size(min = 1) private List<String> slackIds;

	@Schema(description = "내용")
	@NotBlank private String message;
}
