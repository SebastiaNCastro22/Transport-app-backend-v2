package com.transport.app.platform.iotProcess.domain.services;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iam.domain.model.commands.SignInCommand;
import com.transport.app.platform.iam.domain.model.commands.SignUpCommand;
import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.commands.*;
import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface IotProcessCommandService {

    Optional<IotProcess> handle(CreateIotProcessCommand command);
    void handle(UpdateTemperatureCommand command);
    void handle(UpdateWeightCommand command);
    Optional<IotProcess> handle(SignUpIotProcessCommand command);
    Optional<ImmutablePair<IotProcess, String>> handle(SignInIotProcessCommand command);
}
