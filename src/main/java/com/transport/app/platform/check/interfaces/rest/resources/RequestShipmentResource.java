package com.transport.app.platform.check.interfaces.rest.resources;

import jakarta.validation.constraints.NotNull;

public record RequestShipmentResource(
        @NotNull
        String transportId,
        @NotNull
        Long requestId
) {
}
