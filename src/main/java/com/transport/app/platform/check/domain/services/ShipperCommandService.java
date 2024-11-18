package com.transport.app.platform.check.domain.services;

import com.transport.app.platform.check.domain.model.commands.CreateShipperCommand;
import com.transport.app.platform.check.domain.model.valueobjects.ShipperId;

public interface ShipperCommandService {
    ShipperId handle(CreateShipperCommand command);
}
