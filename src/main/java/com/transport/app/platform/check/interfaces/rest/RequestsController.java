package com.transport.app.platform.check.interfaces.rest;

import com.transport.app.platform.check.domain.model.commands.UpdateRequestCommand;
import com.transport.app.platform.check.domain.model.queries.GetRequestByIdQuery;
import com.transport.app.platform.check.domain.services.RequestCommandService;
import com.transport.app.platform.check.domain.services.RequestQueryService;
import com.transport.app.platform.check.interfaces.rest.resources.CreateRequestResource;
import com.transport.app.platform.check.interfaces.rest.resources.RequestResource;
import com.transport.app.platform.check.interfaces.rest.transform.CreateRequestCommandFromResourceAssembler;
import com.transport.app.platform.check.interfaces.rest.transform.RequestResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/request", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Request", description = "Request Management Endpoints")
public class RequestsController {

    private final RequestCommandService requestCommandService;
    private final RequestQueryService requestQueryService;


    public RequestsController(RequestCommandService requestCommandService, RequestQueryService requestQueryService) {
        this.requestCommandService = requestCommandService;
        this.requestQueryService = requestQueryService;
    }

    @PostMapping
    public ResponseEntity<RequestResource> createCourse(@RequestBody CreateRequestResource createRequestResource) {
        var createRequestCommand = CreateRequestCommandFromResourceAssembler.toCommandFromResource(createRequestResource);
        var requestId = requestCommandService.handle(createRequestCommand);
        if (requestId == 0L) {
            return ResponseEntity.badRequest().build();
        }
        var getCourseByIdQuery = new GetRequestByIdQuery(requestId);
        var request = requestQueryService.handle(getCourseByIdQuery);
        if (request.isEmpty()) return ResponseEntity.badRequest().build();
        var requestResource = RequestResourceFromEntityAssembler.toResourceFromEntity(request.get());
        return new ResponseEntity<>(requestResource, HttpStatus.CREATED);
    }
    @GetMapping("/{requestId}")
    public ResponseEntity<RequestResource> getRequestById(@PathVariable Long requestId) {
        var getRequestByIdQuery = new GetRequestByIdQuery(requestId);
        var request = requestQueryService.handle(getRequestByIdQuery);
        if (request.isEmpty()) return ResponseEntity.badRequest().build();
        var requestResource = RequestResourceFromEntityAssembler.toResourceFromEntity(request.get());
        return ResponseEntity.ok(requestResource);
    }
    @PutMapping("/{requestId}")
    public ResponseEntity<?> updateRequest(@PathVariable Long requestId, @RequestBody UpdateRequestCommand command) {
        var getRequestByIdQuery = new GetRequestByIdQuery(requestId);

        try {
            var updatedRequest = requestCommandService.handle(command, getRequestByIdQuery);
            return ResponseEntity.ok(updatedRequest);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while updating request: " + e.getMessage());
        }
    }
}
