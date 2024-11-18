package com.transport.app.platform.iotProcess.interfaces.rest.transform;

import com.transport.app.platform.iotProcess.domain.model.commands.SignInIotProcessCommand;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.SignInIotProcessResource;

public class SignInIotProcessCommandFromResourceAssembler {
    public static SignInIotProcessCommand toCommandFromResource(SignInIotProcessResource resource) {
        return new SignInIotProcessCommand(resource.nameIotDevice(), resource.macAddress());
    }
}
