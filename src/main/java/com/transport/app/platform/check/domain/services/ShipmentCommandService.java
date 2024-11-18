package com.transport.app.platform.check.domain.services;

import com.transport.app.platform.check.domain.model.commands.ConfirmShipmentCommand;
import com.transport.app.platform.check.domain.model.commands.RequestShipmentCommand;

public interface ShipmentCommandService {

    Long handle(RequestShipmentCommand command);
    Long handle(ConfirmShipmentCommand command);
    //Long handle(RejectEnrollmentCommand command);

    //Long handle(CancelEnrollmentCommand command);

    //Long handle(CompleteTutorialForEnrollmentCommand command);
}
