package com.transport.app.platform.check.domain.services;

import com.transport.app.platform.check.domain.model.aggregates.Request;
import com.transport.app.platform.check.domain.model.queries.GetAllRequestsQuery;
import com.transport.app.platform.check.domain.model.queries.GetRequestByIdQuery;

import java.util.List;
import java.util.Optional;

public interface RequestQueryService {
    Optional<Request> handle(GetRequestByIdQuery query);
    List<Request> handle(GetAllRequestsQuery query);
    //Optional<LearningPathItem> handle(GetLearningPathItemByCourseIdAndTutorialIdQuery query);
}
