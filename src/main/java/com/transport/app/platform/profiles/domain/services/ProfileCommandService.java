package com.transport.app.platform.profiles.domain.services;

import com.transport.app.platform.profiles.domain.model.aggregates.Profile;
import com.transport.app.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.transport.app.platform.profiles.domain.model.commands.UpdateProfileCommand;

import java.util.Optional;

public interface ProfileCommandService {
    Optional<Profile> handle(CreateProfileCommand command);
    Optional<Profile> handle(UpdateProfileCommand command);

}
