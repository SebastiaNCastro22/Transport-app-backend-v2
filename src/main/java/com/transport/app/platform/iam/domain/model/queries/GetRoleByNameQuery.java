package com.transport.app.platform.iam.domain.model.queries;

import com.transport.app.platform.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
