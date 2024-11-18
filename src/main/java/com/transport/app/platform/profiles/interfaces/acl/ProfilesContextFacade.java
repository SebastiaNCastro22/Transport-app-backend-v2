package com.transport.app.platform.profiles.interfaces.acl;

import com.transport.app.platform.profiles.domain.model.commands.CreateProfileCommand;
import com.transport.app.platform.profiles.domain.model.queries.GetProfileByEmailQuery;
import com.transport.app.platform.profiles.domain.model.valueobjects.EmailAddress;
import com.transport.app.platform.profiles.domain.services.ProfileCommandService;
import com.transport.app.platform.profiles.domain.services.ProfileQueryService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Service Facade for the Profile context.
 *
 * <p>
 * It is used by the other contexts to interact with the Profile context.
 * It is implemented as part of an anti-corruption layer (ACL) to be consumed by other contexts.
 * </p>
 *
 */
@Service
public class ProfilesContextFacade {
    private final ProfileCommandService profileCommandService;
    private final ProfileQueryService profileQueryService;

    public ProfilesContextFacade(ProfileCommandService profileCommandService, ProfileQueryService profileQueryService) {
        this.profileCommandService = profileCommandService;
        this.profileQueryService = profileQueryService;
    }

    public Long createProfile(String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {
        var createProfileCommand = new CreateProfileCommand(firstName, lastName, email, address, birthday, dni, phone);
        var profile = profileCommandService.handle(createProfileCommand);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();
    }

    public Long fetchProfileIdByEmail(String email) {
        var getProfileByEmailQuery = new GetProfileByEmailQuery(new EmailAddress(email));
        var profile = profileQueryService.handle(getProfileByEmailQuery);
        if (profile.isEmpty()) return 0L;
        return profile.get().getId();

    }
}