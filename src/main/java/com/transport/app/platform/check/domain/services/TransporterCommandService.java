package com.transport.app.platform.check.domain.services;

import com.transport.app.platform.check.domain.model.commands.CreateTransporterCommand;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;

public interface TransporterCommandService {
    TransporterId handle(CreateTransporterCommand command);
    //TransporterId handle(UpdateTransporterMetricsOnRouteCompletedCommand command);
}
