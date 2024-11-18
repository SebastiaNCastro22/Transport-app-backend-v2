package com.transport.app.platform.check.interfaces.rest.resources;

import com.transport.app.platform.check.domain.model.valueobjects.ShipperId;

public record ShipperResource(ShipperId shipperId, Long profileId) {
}
