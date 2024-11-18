package com.transport.app.platform.iotProcess.interfaces.rest;


import com.transport.app.platform.iotProcess.domain.services.IotProcessCommandService;
import com.transport.app.platform.iotProcess.domain.services.IotProcessQueryService;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.CreateIotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.IotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.CreateIotProcessCommandFromResourceAssembler;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.IotProcessResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/iotProcess", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "IotProcess", description = "IotProcess Management Endpoints")
public class IotController {

    private final IotProcessCommandService iotProcessCommandService;
    private final IotProcessQueryService iotProcessQueryService;

    public IotController(IotProcessCommandService iotProcessCommandService, IotProcessQueryService iotProcessQueryService) {
        this.iotProcessCommandService = iotProcessCommandService;
        this.iotProcessQueryService = iotProcessQueryService;
    }

    @PostMapping
    public ResponseEntity<IotProcessResource> createProfile(@RequestBody CreateIotProcessResource resource) {
        var createIotProcessCommand = CreateIotProcessCommandFromResourceAssembler.toCommandFromResource(resource);
        var iotProcess = iotProcessCommandService.handle(createIotProcessCommand);
        if (iotProcess.isEmpty()) return ResponseEntity.badRequest().build();
        var iotProcessResource = IotProcessResourceFromEntityAssembler.toResourceFromEntity(iotProcess.get());
        return new ResponseEntity<>(iotProcessResource, HttpStatus.CREATED);
    }

}
