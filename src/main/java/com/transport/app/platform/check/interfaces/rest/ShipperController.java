package com.transport.app.platform.check.interfaces.rest;

import com.transport.app.platform.check.domain.model.queries.GetShipperByShipperIdQuery;
import com.transport.app.platform.check.domain.model.valueobjects.ShipperId;
import com.transport.app.platform.check.domain.services.ShipperCommandService;
import com.transport.app.platform.check.domain.services.ShipperQueryService;
import com.transport.app.platform.check.interfaces.rest.resources.CreateShipperResource;
import com.transport.app.platform.check.interfaces.rest.resources.ShipperResource;
import com.transport.app.platform.check.interfaces.rest.transform.CreateShipperCommandResourceAssembler;
import com.transport.app.platform.check.interfaces.rest.transform.ShipperResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/shipper", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Shippers", description = "Shipper Management Endpoints")
public class ShipperController {
    private final ShipperCommandService shipperCommandService;
    private final ShipperQueryService shipperQueryService;


    public ShipperController(ShipperCommandService shipperCommandService, ShipperQueryService shipperQueryService) {
        this.shipperCommandService = shipperCommandService;
        this.shipperQueryService = shipperQueryService;
    }

    @PostMapping
    public ResponseEntity<ShipperResource> createUser(@RequestBody CreateShipperResource resource) {
        var createUserCommand = CreateShipperCommandResourceAssembler.toCommandFromResource(resource);
        var shipperId = shipperCommandService.handle(createUserCommand);
        if (shipperId.shipperId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getShipperByShipperIdQuery = new GetShipperByShipperIdQuery(shipperId);
        var user = shipperQueryService.handle(getShipperByShipperIdQuery);
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var userResource = ShipperResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);

    }

    @GetMapping("/{shipperId}")
    public ResponseEntity<ShipperResource> getTransporterByTransporterId(@PathVariable String shipperId) {
        var shipperId1  = new ShipperId(shipperId);
        var getShipperByShipperIdQuery = new GetShipperByShipperIdQuery(shipperId1);
        var shipper = shipperQueryService.handle(getShipperByShipperIdQuery);
        if (shipper.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var shipperResource = ShipperResourceFromEntityAssembler.toResourceFromEntity(shipper.get());
        return ResponseEntity.ok(shipperResource);
    }
}
