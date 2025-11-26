package com.smartlogis.notificationservice.domain.service;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationLogCreate;

public interface NotificationCreateService {
	NotificationLog create(NotificationLogCreate request);
}
