package com.transport.app.platform.profiles.interfaces.rest.transform;

import com.transport.app.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.transport.app.platform.profiles.interfaces.rest.resources.CreateProfileResource;

public class CreateProfileCommandFromResourceAssembler {
    public static CreateProfileCommand toCommandFromResource(CreateProfileResource resource) {
        return new CreateProfileCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.address(),
                resource.birthday(),
                resource.dni(),
                resource.phone());
    }
}
