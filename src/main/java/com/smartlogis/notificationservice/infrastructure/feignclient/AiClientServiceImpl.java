package com.smartlogis.notificationservice.infrastructure.feignclient;

import org.springframework.stereotype.Component;

import com.smartlogis.notificationservice.application.service.AiClientService;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.AiGenerateFeignResponse;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.ClientResponse;
import com.smartlogis.notificationservice.infrastructure.feignclient.dto.DeliveryDeadlineFeignRequest;
import com.smartlogis.notificationservice.infrastructure.feignclient.exception.FeignException;
import com.smartlogis.notificationservice.infrastructure.feignclient.exception.FeignMessageCode;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AiClientServiceImpl implements AiClientService {

	private final AiClient aiClient;

	@Override
	public String generateDeliveryDeadline(DeliveryDeadlineFeignRequest request) {
		try {
			ClientResponse<AiGenerateFeignResponse> response = aiClient
				.generateDeliveryDeadline(request);

			if (response == null) {
				throw new FeignException(FeignMessageCode.INTERNAL_SERVER_ERROR, "AI 서버 응답이 null입니다.");
			}

			if (response.data() == null) {
				throw new FeignException(FeignMessageCode.INTERNAL_SERVER_ERROR, "AI 서버 응답 데이터가 없습니다.");
			}

			return response.data().response();
		} catch (Exception e) {
			throw new FeignException(FeignMessageCode.INTERNAL_SERVER_ERROR, e.getMessage());
		}
	}
}
