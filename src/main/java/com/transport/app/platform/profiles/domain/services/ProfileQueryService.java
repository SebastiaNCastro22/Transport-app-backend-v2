package com.transport.app.platform.profiles.domain.services;

import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import com.transport.app.platform.profiles.domain.model.queries.GetAllProfilesQuery;
import com.transport.app.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.transport.app.platform.profiles.domain.model.queries.GetProfileByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProfileQueryService {
    List<Profile> handle(GetAllProfilesQuery query);
    Optional<Profile> handle(GetProfileByIdQuery query);
    Optional<Profile> handle(GetProfileByEmailQuery query);
}
