package com.smartlogis.notificationservice.domain.service;

import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationCreate;

public interface NotificationCreateService {
	NotificationLog create(NotificationCreate request);
}
