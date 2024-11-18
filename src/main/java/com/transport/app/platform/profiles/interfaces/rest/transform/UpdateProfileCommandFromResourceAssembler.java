package com.transport.app.platform.profiles.interfaces.rest.transform;

import com.transport.app.platform.profiles.domain.model.commands.UpdateProfileCommand;
import com.transport.app.platform.profiles.interfaces.rest.resources.UpdateProfileResource;

public class UpdateProfileCommandFromResourceAssembler {
    public static UpdateProfileCommand toCommandFromResource(Long profileId, UpdateProfileResource resource) {
        return new UpdateProfileCommand(profileId, resource.firstName(), resource.lastName(), resource.email(), resource.address(),resource.birthday() ,resource.dni(), resource.phone());
    }
}
