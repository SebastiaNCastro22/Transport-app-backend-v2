package com.transport.app.platform.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {
}
