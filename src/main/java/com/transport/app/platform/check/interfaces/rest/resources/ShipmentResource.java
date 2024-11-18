package com.transport.app.platform.check.interfaces.rest.resources;

import com.transport.app.platform.check.domain.model.valueobjects.ShipmentStatus;

public record ShipmentResource(Long shipmentId, String transportId, Long requestId, String status) {
}
