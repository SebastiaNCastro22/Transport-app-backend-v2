package com.transport.app.platform.iotProcess.application.internal.commandservices;

import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.RequestRepository;
import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.commands.CreateIotProcessCommand;
import com.transport.app.platform.iotProcess.domain.model.commands.UpdateTemperatureCommand;
import com.transport.app.platform.iotProcess.domain.model.commands.UpdateWeightCommand;
import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import com.transport.app.platform.iotProcess.domain.services.IotProcessCommandService;
import com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories.IotProcessRepository;
import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IotProcessCommandServiceImpl implements IotProcessCommandService {

    private final IotProcessRepository iotProcessRepository;
    private final RequestRepository requestRepository;

    public IotProcessCommandServiceImpl(IotProcessRepository iotProcessRepository, RequestRepository requestRepository) {
        this.iotProcessRepository = iotProcessRepository;
        this.requestRepository = requestRepository;
    }

    @Override
    public Optional<IotProcess> handle(CreateIotProcessCommand command) {
        var iotProcess = new IotProcess(command);
        iotProcessRepository.save(iotProcess);
        return Optional.of(iotProcess);
    }

    @Override
    public void handle(UpdateTemperatureCommand command) {
        var iotProcess = iotProcessRepository.findById(command.iotProcessId())
                .orElseThrow(() -> new IllegalArgumentException("IotProcess not found with ID: " + command.iotProcessId()));

        iotProcess.updateTemperature(command.temperature());
        iotProcessRepository.save(iotProcess);

    }

    @Override
    public void handle(UpdateWeightCommand command) {
        var iotProcess = iotProcessRepository.findById(command.iotProcessId())
                .orElseThrow(() -> new IllegalArgumentException("IotProcess not found with ID: " + command.iotProcessId()));

        iotProcess.updateWeight(command.weight());
        iotProcessRepository.save(iotProcess);
    }

}
