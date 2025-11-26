package com.smartlogis.notificationservice.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.smartlogis.common.utils.QuerydslSortUtils;
import com.smartlogis.notificationservice.domain.NotificationLog;
import com.smartlogis.notificationservice.domain.QNotificationLog;
import com.smartlogis.notificationservice.domain.dto.NotificationSearch;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NotificationRepositoryCustomImpl implements NotificationRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public Page<NotificationLog> getNotifications(NotificationSearch search, Pageable pageable) {
		QNotificationLog notificationLog = QNotificationLog.notificationLog;

		BooleanBuilder condition = new BooleanBuilder();
		if (search.type() != null) {
			condition.and(notificationLog.type.eq(search.type()));
		}
		if (search.status() != null) {
			condition.and(notificationLog.status.eq(search.status()));
		}

		OrderSpecifier<?>[] orders = QuerydslSortUtils.toOrderSpecifiers(notificationLog, "createdAt", pageable.getSort());

		List<NotificationLog> contents = queryFactory
			.selectFrom(notificationLog)
			.where(condition)
			.orderBy(orders)
			.offset(pageable.getOffset())
			.limit(pageable.getPageSize())
			.fetch();

		Long total = Optional.ofNullable(
			queryFactory.select(notificationLog.count()).from(notificationLog).where(condition).fetchOne()
		).orElse(0L);

		return new PageImpl<>(contents, pageable, total);
	}
}
