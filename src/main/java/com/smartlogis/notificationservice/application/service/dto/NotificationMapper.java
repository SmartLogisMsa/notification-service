package com.smartlogis.notificationservice.application.service.dto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.smartlogis.common.presentation.dto.PageRequest;
import com.smartlogis.notificationservice.domain.NotificationStatus;
import com.smartlogis.notificationservice.domain.NotificationType;
import com.smartlogis.notificationservice.domain.dto.NotificationLogSearch;
import com.smartlogis.notificationservice.presentation.dto.NotificationLogSearchRequest;

public class NotificationMapper {

	public static NotificationLogSearch toDomain(NotificationLogSearchRequest request) {
		return new NotificationLogSearch (
			NotificationType.fromString(request.getType()),
			NotificationStatus.fromString(request.getStatus())
		);
	}

	public static Pageable getPageable(PageRequest request) {
		String sortBy = request.getSortBy();
		String direction = request.getDirection();
		int page = request.getPage();
		int size = request.getSize();

		if (sortBy == null || sortBy.isBlank()) {
			return org.springframework.data.domain.PageRequest.of(page, size);
		}

		Sort sort = "DESC".equalsIgnoreCase(direction)
			? Sort.by(sortBy).descending()
			: Sort.by(sortBy).ascending();

		return org.springframework.data.domain.PageRequest.of(page, size, sort);
	}
}
