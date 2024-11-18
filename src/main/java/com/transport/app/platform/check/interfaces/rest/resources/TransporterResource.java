package com.transport.app.platform.check.interfaces.rest.resources;

import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;

public record TransporterResource(TransporterId transporterId, Long profileId) {
}
