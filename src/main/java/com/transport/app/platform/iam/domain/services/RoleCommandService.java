package com.transport.app.platform.iam.domain.services;

import com.transport.app.platform.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
