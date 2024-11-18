package com.transport.app.platform.check.application.internal.commandservices;

import com.transport.app.platform.check.application.internal.outboundservices.acl.ExternalIotProcessService;
import com.transport.app.platform.check.application.internal.outboundservices.acl.ExternalProfileService;
import com.transport.app.platform.check.domain.model.aggregates.Request;
import com.transport.app.platform.check.domain.model.commands.CreateRequestCommand;
import com.transport.app.platform.check.domain.model.commands.UpdateRequestCommand;
import com.transport.app.platform.check.domain.model.queries.GetRequestByIdQuery;
import com.transport.app.platform.check.domain.services.RequestCommandService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestCommandServiceImpl implements RequestCommandService {

    private final RequestRepository requestRepository;
    private final ExternalIotProcessService externalIotProcessService;

    public RequestCommandServiceImpl(RequestRepository requestRepository,ExternalIotProcessService externalIotProcessService) {
        this.requestRepository = requestRepository;
        this.externalIotProcessService = externalIotProcessService;
    }

    @Override
    public Long handle(CreateRequestCommand command) {
        var iotProcessId = externalIotProcessService.createIotProcess(command.idealTemperature(), command.idealWeight());
        var request = new Request(command);
        try {
            requestRepository.save(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving request: " + e.getMessage());
        }
        return request.getId();
    }
    @Override
    public Optional<Request> handle(UpdateRequestCommand command, GetRequestByIdQuery query) {

        var request = requestRepository.findById(query.requestId())
                .orElseThrow(() -> new IllegalArgumentException("Request not found with ID: " + query.requestId()));

        // Actualiza los valores de temperatura y peso en el objeto Request
        if (command.updatedTemperature() != null) {
            request.updateTemperature(command.updatedTemperature());
        }
        if (command.updatedWeight() != null) {
            request.updateWeight(command.updatedWeight());
        }
        externalIotProcessService.updateIotProcess(command.iotProcessId(), command.updatedTemperature(), command.updatedWeight());

        try {
            requestRepository.save(request);
            return Optional.of(request);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating request: " + e.getMessage());
        }
    }


}
