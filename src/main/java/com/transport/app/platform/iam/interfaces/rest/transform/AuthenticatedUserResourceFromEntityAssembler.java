package com.transport.app.platform.iam.interfaces.rest.transform;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(Client entity, String token) {
        return new AuthenticatedUserResource(entity.getId(), entity.getUsername(), token);
    }
}
