package com.transport.app.platform.iotProcess.interfaces.rest.resources;

public record IotProcessResource(
        long id,
        Double temperature,
        Double weight
) {
}
