package com.transport.app.platform.iotProcess.interfaces.rest.transform;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.IotProcessResource;
import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import com.transport.app.platform.profiles.interfaces.rest.resources.ProfileResource;

public class IotProcessResourceFromEntityAssembler {
    public static IotProcessResource toResourceFromEntity(IotProcess entity) {
        return new IotProcessResource(
                entity.getId(),
                entity.getTemperature(),
                entity.getWeight());
    }
}
