package com.transport.app.platform.check.interfaces.rest;

import com.transport.app.platform.check.domain.model.queries.GetTransporterByTransporterIdQuery;
import com.transport.app.platform.check.domain.services.TransporterCommandService;
import com.transport.app.platform.check.domain.services.TransporterQueryService;
import com.transport.app.platform.check.interfaces.rest.resources.CreateTransporterResource;
import com.transport.app.platform.check.interfaces.rest.resources.TransporterResource;
import com.transport.app.platform.check.interfaces.rest.transform.CreateTransporterCommandResourceAssembler;
import com.transport.app.platform.check.interfaces.rest.transform.TransporterResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/transporter", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Transporter", description = "Transporter Management Endpoints")
public class TransporterController {

    private final TransporterCommandService transporterCommandService;
    private final TransporterQueryService transporterQueryService;


    public TransporterController(TransporterCommandService transporterCommandService, TransporterQueryService transporterQueryService) {
        this.transporterCommandService = transporterCommandService;
        this.transporterQueryService = transporterQueryService;
    }

    @PostMapping
    public ResponseEntity<TransporterResource> createTransporter(@RequestBody CreateTransporterResource resource) {
        var createTransporterCommand = CreateTransporterCommandResourceAssembler.toCommandFromResource(resource);
        var transporterId = transporterCommandService.handle(createTransporterCommand);
        if (transporterId.trasnporterId().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var getTransporterByTransporterIdQuery = new GetTransporterByTransporterIdQuery(transporterId);
        var transporter = transporterQueryService.handle(getTransporterByTransporterIdQuery);
        if (transporter.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var studentResource = TransporterResourceFromEntityAssembler.toResourceFromEntity(transporter.get());
        return new ResponseEntity<>(studentResource, HttpStatus.CREATED);

    }

}
