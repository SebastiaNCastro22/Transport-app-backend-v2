package com.transport.app.platform.check.interfaces.rest.transform;

import com.transport.app.platform.check.domain.model.aggregates.Shipper;
import com.transport.app.platform.check.interfaces.rest.resources.ShipperResource;

public class ShipperResourceFromEntityAssembler {
    public static ShipperResource toResourceFromEntity(Shipper shipper) {
        return new ShipperResource(
                shipper.getShipperId(),
                shipper.getProfileId()
        );
    }
}
