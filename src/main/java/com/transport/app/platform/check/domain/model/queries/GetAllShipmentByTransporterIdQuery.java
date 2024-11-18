package com.transport.app.platform.check.domain.model.queries;

import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;

public record GetAllShipmentByTransporterIdQuery(TransporterId transporterId) {
}
