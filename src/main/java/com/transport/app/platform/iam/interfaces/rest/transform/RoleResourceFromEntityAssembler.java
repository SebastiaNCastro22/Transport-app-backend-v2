package com.transport.app.platform.iam.interfaces.rest.transform;

import com.transport.app.platform.iam.domain.model.entities.Role;
import com.transport.app.platform.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role entity) {
        return new RoleResource(entity.getId(), entity.getStringName());

    }
}
