package com.transport.app.platform.check.interfaces.rest.transform;

import com.transport.app.platform.check.domain.model.commands.CreateRequestCommand;
import com.transport.app.platform.check.domain.model.valueobjects.RequestStatus;
import com.transport.app.platform.check.interfaces.rest.resources.CreateRequestResource;

public class CreateRequestCommandFromResourceAssembler {
    public static CreateRequestCommand toCommandFromResource(CreateRequestResource resource) {
        return new CreateRequestCommand(resource.startLocation(), resource.arrivalPlace(), resource.descriptionRequest(), resource.idealTemperature(), resource.idealWeight(), resource.offeredPrice());
    }
}
