package com.transport.app.platform.iam.domain.services;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iam.domain.model.queries.GetAllClientsQuery;
import com.transport.app.platform.iam.domain.model.queries.GetClientByIdQuery;
import com.transport.app.platform.iam.domain.model.queries.GetClientByUsernameQuery;

import java.util.List;
import java.util.Optional;

public interface ClientQueryService {
    List<Client> handle(GetAllClientsQuery query);
    Optional<Client> handle(GetClientByIdQuery query);
    Optional<Client> handle(GetClientByUsernameQuery query);
}
