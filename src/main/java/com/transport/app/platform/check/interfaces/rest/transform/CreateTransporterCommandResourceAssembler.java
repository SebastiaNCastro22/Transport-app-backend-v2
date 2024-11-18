package com.transport.app.platform.check.interfaces.rest.transform;

import com.transport.app.platform.check.domain.model.commands.CreateTransporterCommand;
import com.transport.app.platform.check.interfaces.rest.resources.CreateTransporterResource;

public class CreateTransporterCommandResourceAssembler {
    public static CreateTransporterCommand toCommandFromResource(CreateTransporterResource resource) {
        return new CreateTransporterCommand(
                resource.firstName(),
                resource.lastName(),
                resource.email(),
                resource.address(),
                resource.birthday(),
                resource.dni(),
                resource.phone()
        );
    }
}
