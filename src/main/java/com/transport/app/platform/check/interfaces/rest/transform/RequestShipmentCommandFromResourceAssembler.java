package com.transport.app.platform.check.interfaces.rest.transform;

import com.transport.app.platform.check.domain.model.commands.RequestShipmentCommand;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import com.transport.app.platform.check.interfaces.rest.resources.RequestShipmentResource;

public class RequestShipmentCommandFromResourceAssembler {
    public static RequestShipmentCommand toCommandFromResource(RequestShipmentResource resource) {
        return new RequestShipmentCommand(new TransporterId(resource.transportId()), resource.requestId());
    }
}
