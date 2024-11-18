package com.transport.app.platform.iotProcess.interfaces.rest;




import com.transport.app.platform.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;

import com.transport.app.platform.iotProcess.domain.services.IotProcessCommandService;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.AuthenticatedIotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.IotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.SignInIotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.resources.SignUpIotProcessResource;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.AuthenticatedIotProcessResourceFromEntityAssembler;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.IotProcessResourceFromEntityAssembler;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.SignInIotProcessCommandFromResourceAssembler;
import com.transport.app.platform.iotProcess.interfaces.rest.transform.SignUpIotProcessCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/authentication-iot-process", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Auth Iot Devices", description = "Authentication")
public class AuthIotProcessController {

    private final IotProcessCommandService iotProcessCommandService;

    public AuthIotProcessController(IotProcessCommandService iotProcessCommandService) {
        this.iotProcessCommandService = iotProcessCommandService;
    }

    /**
     * Endpoint para registrar un nuevo dispositivo IoT.
     *
     * @param resource Datos del dispositivo IoT para el registro.
     * @return Información del dispositivo registrado.
     */
    @PostMapping("/sign-up-iot-device")
    public ResponseEntity<IotProcessResource> signUp(@RequestBody SignUpIotProcessResource resource) {
        var command = SignUpIotProcessCommandFromResourceAssembler.toCommandFromResource(resource);

        try {
            var iotDevice = iotProcessCommandService.handle(command);
            var responseResource = IotProcessResourceFromEntityAssembler.toResourceFromEntity(iotDevice.get());
            return new ResponseEntity<>(responseResource, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Mensaje opcional en un ResponseResource si lo deseas.
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Endpoint para autenticar un dispositivo IoT.
     *
     * @param resource Credenciales del dispositivo IoT.
     * @return Información del dispositivo autenticado y token JWT.
     */
    @PostMapping("/sign-in-iot-device")
    public ResponseEntity<AuthenticatedIotProcessResource> signIn(@RequestBody SignInIotProcessResource resource) {
        var command = SignInIotProcessCommandFromResourceAssembler.toCommandFromResource(resource);

        try {
            var authenticatedIotProcess = iotProcessCommandService.handle(command);
            if (authenticatedIotProcess.isEmpty()) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }

            var responseResource = AuthenticatedIotProcessResourceFromEntityAssembler.toResourceFromEntity(
                    authenticatedIotProcess.get().getLeft(),
                    authenticatedIotProcess.get().getRight()
            );

            return ResponseEntity.ok(responseResource);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
