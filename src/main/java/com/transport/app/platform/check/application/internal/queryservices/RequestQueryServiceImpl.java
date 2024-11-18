package com.transport.app.platform.check.application.internal.queryservices;

import com.transport.app.platform.check.domain.model.aggregates.Request;
import com.transport.app.platform.check.domain.model.queries.GetAllRequestsQuery;
import com.transport.app.platform.check.domain.model.queries.GetRequestByIdQuery;
import com.transport.app.platform.check.domain.services.RequestQueryService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestQueryServiceImpl implements RequestQueryService {

    private final RequestRepository requestRepository;

    public RequestQueryServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }
    @Override
    public Optional<Request> handle(GetRequestByIdQuery query) {
        return requestRepository.findById(query.requestId());
    }
    @Override
    public List<Request> handle(GetAllRequestsQuery query) {
        return requestRepository.findAll();
    }
}
