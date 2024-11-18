package com.transport.app.platform.check.domain.services;

import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface ShipmentQueryService {
    List<Shipment> handle(GetAllShipmentsByClientQuery query);
    //Optional<Enrollment> handle(GetEnrollmentByIdQuery query);
    List<Shipment> handle(GetAllShipmentsQuery query);
    List<Shipment> handle(GetAllShipmentByRequestIdQuery query);
    Optional<Shipment> handle(GetShipmentByTransporterIdAndRequestIdQuery query);
    List<Shipment> handle(GetAllShipmentByTransporterIdQuery query);
}
