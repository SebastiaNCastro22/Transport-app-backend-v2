package com.transport.app.platform.check.interfaces.rest;


import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.domain.model.commands.ConfirmShipmentCommand;
import com.transport.app.platform.check.domain.model.queries.GetAllShipmentsQuery;
import com.transport.app.platform.check.domain.model.queries.GetShipmentByTransporterIdAndRequestIdQuery;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import com.transport.app.platform.check.domain.services.ShipmentCommandService;
import com.transport.app.platform.check.domain.services.ShipmentQueryService;
import com.transport.app.platform.check.interfaces.rest.resources.RequestShipmentResource;
import com.transport.app.platform.check.interfaces.rest.resources.ShipmentResource;
import com.transport.app.platform.check.interfaces.rest.transform.RequestShipmentCommandFromResourceAssembler;
import com.transport.app.platform.check.interfaces.rest.transform.ShipmentResourceFromEntityAssembler;
import com.transport.app.platform.shared.interfaces.rest.resources.MessageResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/shipment", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shipment", description = "Shipment Management Endpoints")
public class ShipmentController {

    private final ShipmentCommandService shipmentCommandService;
    private final ShipmentQueryService shipmentQueryService;

    public ShipmentController(ShipmentCommandService shipmentCommandService, ShipmentQueryService shipmentQueryService) {
        this.shipmentCommandService = shipmentCommandService;
        this.shipmentQueryService = shipmentQueryService;
    }

    @PostMapping
    public ResponseEntity<ShipmentResource> requestShipment(@RequestBody RequestShipmentResource resource) {
        var command = RequestShipmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var shipmentId = shipmentCommandService.handle(command);
        System.out.println("Shipment ID: " + shipmentId);
        var getEnrollmentByAcmeStudentRecordIdAndCourseIdQuery = new GetShipmentByTransporterIdAndRequestIdQuery(new TransporterId(resource.transportId()), resource.requestId());
        var shipment = shipmentQueryService.handle(getEnrollmentByAcmeStudentRecordIdAndCourseIdQuery);
        if (shipment.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var enrollmentResource = ShipmentResourceFromEntityAssembler.toResourceFromEntity(shipment.get());
        return new ResponseEntity<>(enrollmentResource, HttpStatus.CREATED);
    }

    @PostMapping("/{shipmentId}/confirmations")
    public ResponseEntity<MessageResource> confirmEnrollment(@PathVariable Long shipmentId) {
        var confirmEnrollmentCommand = new ConfirmShipmentCommand(shipmentId);
        shipmentCommandService.handle(confirmEnrollmentCommand);
        return ResponseEntity.ok(new MessageResource("Confirmed Enrollment ID: " + shipmentId));
    }
/*
    @PostMapping("/{shipmentId}/rejections")
    public ResponseEntity<MessageResource> rejectShipment(@PathVariable Long shipmentId) {
        var rejectShipmentCommand = new RejectShipmentCommand(shipmentId);
        shipmentCommandService.handle(rejectShipmentCommand);
        return ResponseEntity.ok(new MessageResource("Rejected Shipment ID: " + shipmentId));
    }
*/

    @GetMapping
    public ResponseEntity<List<ShipmentResource>> getAllEnrollments() {
        var getAllShipmentsQuery = new GetAllShipmentsQuery();
        var shipments = shipmentQueryService.handle(getAllShipmentsQuery);
        var shipmentResources = shipments.stream().map(ShipmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(shipmentResources);
    }

}
