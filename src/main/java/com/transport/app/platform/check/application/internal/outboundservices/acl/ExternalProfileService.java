package com.transport.app.platform.check.application.internal.outboundservices.acl;

import com.transport.app.platform.check.domain.model.valueobjects.ProfileId;
import com.transport.app.platform.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ExternalProfileService {
    private ProfilesContextFacade profilesContextFacade;

    public ExternalProfileService(ProfilesContextFacade profilesContextFacade) {
        this.profilesContextFacade = profilesContextFacade;
    }

    public Optional<ProfileId> fetchProfileIdByEmail(String email) {
        var profileId = profilesContextFacade.fetchProfileIdByEmail(email);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }

    public Optional<ProfileId> createProfile(String firstName, String lastName, String email, String address, Date birthday, long dni, String phone) {
        var profileId = profilesContextFacade.createProfile(firstName, lastName, email, address, birthday, dni, phone);
        if (profileId == 0L) return Optional.empty();
        return Optional.of(new ProfileId(profileId));
    }
}
