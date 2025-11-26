package com.smartlogis.notificationservice.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationSearch;
import com.smartlogis.notificationservice.domain.exception.NotificationLogException;
import com.smartlogis.notificationservice.domain.exception.NotificationLogMessageCode;
import com.smartlogis.notificationservice.domain.repository.NotificationLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class NotificationQueryServiceImpl implements NotificationQueryService {

	private final NotificationLogRepository repository;

	@Override
	public NotificationLog getById(Long id) {
		return repository.findById(id)
			.orElseThrow(() -> new NotificationLogException(NotificationLogMessageCode.NOTIFICATION_NOT_FOUND));
	}

	@Override
	public Page<NotificationLog> getNotificationLogs(NotificationSearch search, Pageable pageable) {
		return repository.getNotifications(search, pageable);
	}
}
