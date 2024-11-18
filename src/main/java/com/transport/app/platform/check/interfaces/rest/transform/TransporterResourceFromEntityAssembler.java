package com.transport.app.platform.check.interfaces.rest.transform;

import com.transport.app.platform.check.domain.model.aggregates.Transporter;
import com.transport.app.platform.check.interfaces.rest.resources.TransporterResource;

public class TransporterResourceFromEntityAssembler {
    public static TransporterResource toResourceFromEntity(Transporter transporter) {
        return new TransporterResource(
                transporter.getTransporterId(),
                transporter.getProfileId()
        );
    }
}
