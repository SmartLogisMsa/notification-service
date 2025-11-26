package com.smartlogis.notificationservice.infrastructure.feignclient;

import org.springframework.stereotype.Component;

import com.smartlogis.common.presentation.ApiResponse;
import com.smartlogis.notificationservice.application.service.AiClientService;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.AiGenerateFeignResponse;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.DeliveryDeadlineFeignRequest;
import com.smartlogis.notificationservice.infrastructure.feignclient.exception.FeignException;
import com.smartlogis.notificationservice.infrastructure.feignclient.exception.FeignMessageCode;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AiClientServiceImpl implements AiClientService {

	private final AiClient aiClient;

	@Override
	public String generateDeliveryDeadline(DeliveryDeadlineFeignRequest request) {
		try {
			ApiResponse<AiGenerateFeignResponse> response = aiClient
				.generateDeliveryDeadline(request)
				.getBody();

			if (response == null) {
				throw new FeignException(FeignMessageCode.INTERNAL_SERVER_ERROR, "AI 서버 응답이 null입니다.");
			}

			if (response.getData() == null) {
				throw new FeignException(FeignMessageCode.INTERNAL_SERVER_ERROR, "AI 서버 응답 데이터가 없습니다.");
			}

			return response.getData().response();
		} catch (Exception e) {
			throw new FeignException(FeignMessageCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
