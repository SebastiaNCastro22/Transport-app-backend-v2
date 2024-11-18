package com.transport.app.platform.iotProcess.interfaces.rest.transform;

import com.transport.app.platform.iotProcess.domain.model.commands.SignUpIotProcessCommand;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.SignUpIotProcessResource;


public class SignUpIotProcessCommandFromResourceAssembler {
    public static SignUpIotProcessCommand toCommandFromResource(SignUpIotProcessResource resource) {
        return new SignUpIotProcessCommand(resource.nameIotDevice(), resource.macAddress());
    }
}
