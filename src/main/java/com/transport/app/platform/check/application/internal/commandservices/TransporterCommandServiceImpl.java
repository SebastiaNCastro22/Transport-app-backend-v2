package com.transport.app.platform.check.application.internal.commandservices;

import com.transport.app.platform.check.application.internal.outboundservices.acl.ExternalProfileService;
import com.transport.app.platform.check.domain.model.aggregates.Transporter;
import com.transport.app.platform.check.domain.model.commands.CreateTransporterCommand;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import com.transport.app.platform.check.domain.services.TransporterCommandService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.TransporterRepository;
import org.springframework.stereotype.Service;

@Service
public class TransporterCommandServiceImpl implements TransporterCommandService {

    private final TransporterRepository transporterRepository;

    private final ExternalProfileService externalProfileService;

    public TransporterCommandServiceImpl(TransporterRepository transporterRepository, ExternalProfileService externalProfileService) {
        this.transporterRepository = transporterRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public TransporterId handle (CreateTransporterCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        // If profileId is empty, create profile
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.firstName(), command.lastName(), command.email(), command.address(), command.birthday(), command.dni(), command.phone());
        } else {
            // If profileId is not empty, check if student exists
            transporterRepository.findByProfileId(profileId.get()).ifPresent(transporter -> {
                throw new IllegalArgumentException("Student already exists");
            });
        }
        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");
        var transporter = new Transporter(profileId.get());
        transporterRepository.save(transporter);
        return transporter.getTransporterId();
    }

}
