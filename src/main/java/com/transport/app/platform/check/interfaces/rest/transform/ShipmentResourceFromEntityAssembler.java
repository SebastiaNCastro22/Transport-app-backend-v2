package com.transport.app.platform.check.interfaces.rest.transform;

import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.interfaces.rest.resources.ShipmentResource;

public class ShipmentResourceFromEntityAssembler {
    public static ShipmentResource toResourceFromEntity(Shipment entity) {
        return new ShipmentResource(entity.getId(), entity.getTransporterId().trasnporterId(), entity.getRequest().getId(), entity.getStatus());
    }
}
