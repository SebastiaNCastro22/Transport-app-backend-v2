package com.transport.app.platform.check.application.internal.queryservices;

import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.domain.model.queries.*;
import com.transport.app.platform.check.domain.services.ShipmentQueryService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.ShipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShipmentQueryServiceImpl implements ShipmentQueryService {

    private final ShipmentRepository shipmentRepository;

    public ShipmentQueryServiceImpl(ShipmentRepository shipmentRepository) {
        this.shipmentRepository = shipmentRepository;
    }
    public Optional<Shipment> handle(GetShipmentByIdQuery query) {
        return shipmentRepository.findById(query.shipmentId());
    }

    //@Override
    //public List<Shipment> handle(GetAllShipmentsByClientQuery query) {
        //return shipmentRepository.findAllByClientId(query.clientId());
    //}

    @Override
    public List<Shipment> handle(GetAllShipmentsByClientQuery query) {
        return List.of();
    }

    @Override
    public List<Shipment> handle(GetAllShipmentsQuery query) {
        return shipmentRepository.findAll();
    }

    @Override
    public Optional<Shipment> handle(GetShipmentByTransporterIdAndRequestIdQuery query) {
        return shipmentRepository.findByTransporterIdAndRequestId(query.transporterId(), query.requestId());
    }
    @Override
    public List<Shipment> handle(GetAllShipmentByRequestIdQuery query) {
        return shipmentRepository.findAllByRequestId(query.requestId());
    }
    @Override
    public List<Shipment> handle(GetAllShipmentByTransporterIdQuery query) {
        return shipmentRepository.findAllByTransporterId(query.transporterId());
    }
}
