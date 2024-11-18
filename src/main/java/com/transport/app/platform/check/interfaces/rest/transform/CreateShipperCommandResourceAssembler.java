package com.transport.app.platform.check.interfaces.rest.transform;


import com.transport.app.platform.check.domain.model.commands.CreateShipperCommand;
import com.transport.app.platform.check.interfaces.rest.resources.CreateShipperResource;

public class CreateShipperCommandResourceAssembler {
    public static CreateShipperCommand toCommandFromResource(CreateShipperResource resource) {
        return new CreateShipperCommand(
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
