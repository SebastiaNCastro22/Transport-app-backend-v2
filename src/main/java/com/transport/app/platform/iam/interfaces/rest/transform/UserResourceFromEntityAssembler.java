package com.transport.app.platform.iam.interfaces.rest.transform;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iam.domain.model.entities.Role;
import com.transport.app.platform.iam.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(Client entity) {
        var roles = entity.getRoles().stream().map(Role::getStringName).toList();
        return new UserResource(entity.getId(), entity.getUsername(), roles);
    }
}
