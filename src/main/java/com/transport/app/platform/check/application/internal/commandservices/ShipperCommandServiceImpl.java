package com.transport.app.platform.check.application.internal.commandservices;
import com.transport.app.platform.check.application.internal.outboundservices.acl.ExternalProfileService;
import com.transport.app.platform.check.domain.model.aggregates.Shipper;
import com.transport.app.platform.check.domain.model.commands.CreateShipperCommand;
import com.transport.app.platform.check.domain.model.valueobjects.ShipperId;
import com.transport.app.platform.check.domain.services.ShipperCommandService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.ShipperRepository;

import org.springframework.stereotype.Service;

@Service
public class ShipperCommandServiceImpl implements ShipperCommandService {

    private final ShipperRepository shipperRepository;

    private final ExternalProfileService externalProfileService;

    public ShipperCommandServiceImpl(ShipperRepository shipperRepository, ExternalProfileService externalProfileService) {
        this.shipperRepository = shipperRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public ShipperId handle (CreateShipperCommand command) {
        var profileId = externalProfileService.fetchProfileIdByEmail(command.email());
        // If profileId is empty, create profile
        if (profileId.isEmpty()) {
            profileId = externalProfileService.createProfile(command.firstName(), command.lastName(), command.email(), command.address(), command.birthday(), command.dni(), command.phone());
        } else {
            // If profileId is not empty, check if student exists
            shipperRepository.findByProfileId(profileId.get()).ifPresent(user -> {
                throw new IllegalArgumentException("User already exists");
            });
        }
        if (profileId.isEmpty()) throw new IllegalArgumentException("Unable to create profile");
        var shipper = new Shipper(profileId.get());
        shipperRepository.save(shipper);
        return shipper.getShipperId();
    }
}
