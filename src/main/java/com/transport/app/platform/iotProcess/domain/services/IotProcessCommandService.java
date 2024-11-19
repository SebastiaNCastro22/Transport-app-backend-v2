package com.transport.app.platform.iotProcess.domain.services;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.commands.CreateIotProcessCommand;
import com.transport.app.platform.iotProcess.domain.model.commands.UpdateTemperatureCommand;
import com.transport.app.platform.iotProcess.domain.model.commands.UpdateWeightCommand;
import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import com.transport.app.platform.profiles.domain.model.aggregates.Profile;

import java.util.Optional;

public interface IotProcessCommandService {
    Optional<IotProcess> handle(CreateIotProcessCommand command);
    void handle(UpdateTemperatureCommand command);
    void handle(UpdateWeightCommand command);
}
