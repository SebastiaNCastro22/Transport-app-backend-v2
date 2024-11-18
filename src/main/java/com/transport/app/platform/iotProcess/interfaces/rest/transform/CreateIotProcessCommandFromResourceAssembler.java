package com.transport.app.platform.iotProcess.interfaces.rest.transform;

import com.transport.app.platform.iotProcess.domain.model.commands.CreateIotProcessCommand;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.CreateIotProcessResource;

public class CreateIotProcessCommandFromResourceAssembler {
    public static CreateIotProcessCommand toCommandFromResource(CreateIotProcessResource resource) {
        return new CreateIotProcessCommand(
                resource.temperature(),
                resource.weight());
    }
}
