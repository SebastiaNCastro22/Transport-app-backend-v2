package com.transport.app.platform.iotProcess.interfaces.rest.transform;


import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.AuthenticatedIotProcessResource;

public class AuthenticatedIotProcessResourceFromEntityAssembler {
    public static AuthenticatedIotProcessResource toResourceFromEntity(IotProcess entity, String token) {
        return new AuthenticatedIotProcessResource(entity.getId(), entity.getNameIotDevice(), token);
    }
}
