package com.transport.app.platform.check.domain.model.queries;

import com.transport.app.platform.check.domain.model.valueobjects.ProfileId;

public record GetShipperByProfileIdQuery(ProfileId profileId) {
}