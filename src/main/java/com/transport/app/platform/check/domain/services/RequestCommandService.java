package com.transport.app.platform.check.domain.services;

import com.transport.app.platform.check.domain.model.aggregates.Request;
import com.transport.app.platform.check.domain.model.commands.CreateRequestCommand;
import com.transport.app.platform.check.domain.model.commands.UpdateRequestCommand;
import com.transport.app.platform.check.domain.model.queries.GetRequestByIdQuery;

import java.util.Optional;

public interface RequestCommandService {

    Long handle(CreateRequestCommand command);
    Optional<Request> handle(UpdateRequestCommand command, GetRequestByIdQuery query);
    //void handle(DeleteRequestCommand command);

}
