package com.smartlogis.notificationservice.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationCreate;
import com.smartlogis.notificationservice.domain.repository.NotificationLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class NotificationCreateServiceImpl implements NotificationCreateService {

	private final NotificationLogRepository repository;

	@Override
	public NotificationLog create(NotificationCreate request) {
		NotificationLog log = NotificationLog.create(request);

		repository.save(log);

		return log;
	}
}
