package com.transport.app.platform.check.application.internal.commandservices;

import com.transport.app.platform.check.domain.exceptions.RequestNotFoundException;
import com.transport.app.platform.check.domain.model.aggregates.Request;
import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.domain.model.commands.ConfirmShipmentCommand;
import com.transport.app.platform.check.domain.model.commands.RequestShipmentCommand;
import com.transport.app.platform.check.domain.services.ShipmentCommandService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.RequestRepository;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.ShipmentRepository;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.TransporterRepository;
import org.springframework.stereotype.Service;

@Service
public class ShipmentCommandServiceImpl implements ShipmentCommandService {

    private final RequestRepository requestRepository;

    private final TransporterRepository transporterRepository;
    private final ShipmentRepository shipmentRepository;

    public ShipmentCommandServiceImpl(RequestRepository requestRepository, TransporterRepository transporterRepository, ShipmentRepository shipmentRepository) {
        this.requestRepository = requestRepository;
        this.transporterRepository = transporterRepository;
        this.shipmentRepository = shipmentRepository;
    }

    public Long handle(RequestShipmentCommand command) {
        transporterRepository.findByTransporterId(command.transporterId()).map(transporter -> {
            Request request = requestRepository.findById(command.requestId()).orElseThrow(() -> new RequestNotFoundException(command.requestId()));
            Shipment shipment = new Shipment(command.transporterId(), request);
            shipment = shipmentRepository.save(shipment);
            return shipment.getId();
        }).orElseThrow(() -> new RuntimeException("Transport not found"));
        return 0L;
    }

    @Override
    public Long handle(ConfirmShipmentCommand command) {
        shipmentRepository.findById(command.shipmentId()).map(shipment -> {
            shipment.confirm();
            shipmentRepository.save(shipment);
            return command.shipmentId();
        }).orElseThrow(() -> new RuntimeException("Shipment not found"));
        return null;
    }


}
