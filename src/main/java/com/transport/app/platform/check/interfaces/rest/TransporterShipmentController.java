package com.transport.app.platform.check.interfaces.rest;

import com.transport.app.platform.check.domain.model.queries.GetAllShipmentByTransporterIdQuery;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import com.transport.app.platform.check.domain.services.ShipmentQueryService;
import com.transport.app.platform.check.interfaces.rest.resources.ShipmentResource;
import com.transport.app.platform.check.interfaces.rest.transform.ShipmentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/transport/{transportId}/shipments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Shipments")
public class TransporterShipmentController {

    private final ShipmentQueryService shipmentQueryService;


    public TransporterShipmentController(ShipmentQueryService shipmentQueryService) {
        this.shipmentQueryService = shipmentQueryService;
    }

    @GetMapping
    public ResponseEntity<List<ShipmentResource>> getShipmentForTransportWithTransportId(@PathVariable String transportId) {
        var transporterId = new TransporterId(transportId);
        var getAllEnrollmentsByAcmeStudentRecordIdQuery = new GetAllShipmentByTransporterIdQuery(transporterId);
        var shipments = shipmentQueryService.handle(getAllEnrollmentsByAcmeStudentRecordIdQuery);
        var shipmentResources = shipments.stream().map(ShipmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(shipmentResources);
    }

}
