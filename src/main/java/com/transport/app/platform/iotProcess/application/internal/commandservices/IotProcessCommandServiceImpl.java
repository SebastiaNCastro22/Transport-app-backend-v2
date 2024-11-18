package com.transport.app.platform.iotProcess.application.internal.commandservices;

import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.RequestRepository;
import com.transport.app.platform.iam.domain.model.commands.SignInCommand;
import com.transport.app.platform.iam.domain.model.commands.SignUpCommand;
import com.transport.app.platform.iotProcess.application.internal.outboundservices.hashing.HashingIotProcessService;
import com.transport.app.platform.iotProcess.application.internal.outboundservices.tokens.TokenIotProcessService;
import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.commands.*;
import com.transport.app.platform.iotProcess.domain.services.IotProcessCommandService;
import com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories.IotProcessRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class IotProcessCommandServiceImpl implements IotProcessCommandService {



    private final IotProcessRepository iotProcessRepository;
    private final RequestRepository requestRepository;
    private final TokenIotProcessService tokenIotProcessService;

    public IotProcessCommandServiceImpl(
            IotProcessRepository iotProcessRepository,
            RequestRepository requestRepository,
            TokenIotProcessService tokenIotProcessService) {
        this.iotProcessRepository = iotProcessRepository;
        this.requestRepository = requestRepository;
        this.tokenIotProcessService = tokenIotProcessService;
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


    @Override
    public Optional<IotProcess> handle(SignUpIotProcessCommand command) {

        if (iotProcessRepository.findByNameIotDevice(command.nameIotDevice()).isPresent()) {
            throw new IllegalArgumentException("IoT device already exists with name: " + command.nameIotDevice());
        }

        var newIotProcess = new IotProcess(command.nameIotDevice(), command.macAddress(), 0.0, 0.0);
        iotProcessRepository.save(newIotProcess);

        return Optional.of(newIotProcess);
    }


    @Override
    public Optional<ImmutablePair<IotProcess, String>> handle(SignInIotProcessCommand command) {

        var iotDevice = iotProcessRepository.findByNameIotDevice(command.nameIotDevice())
                .orElseThrow(() -> new IllegalArgumentException("IoT device not found with name: " + command.nameIotDevice()));

        if (!iotDevice.getMacAddress().equals(command.macAddress())) {
            throw new IllegalArgumentException("Invalid MAC address for IoT device: " + command.nameIotDevice());
        }

        var token = tokenIotProcessService.generateToken(iotDevice.getNameIotDevice());

        return Optional.of(ImmutablePair.of(iotDevice, token));
    }

    private IotProcess findIotProcessById(Long id) {
        return iotProcessRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("IoT Process not found with ID: " + id));
    }




}
