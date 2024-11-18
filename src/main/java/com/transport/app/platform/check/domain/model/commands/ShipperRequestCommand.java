package com.transport.app.platform.check.domain.model.commands;

import com.transport.app.platform.check.domain.model.valueobjects.ShipperId;

public record ShipperRequestCommand(ShipperId shipperId) {
}
