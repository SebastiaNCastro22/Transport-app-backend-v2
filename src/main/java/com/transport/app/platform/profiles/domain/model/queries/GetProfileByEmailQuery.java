package com.transport.app.platform.profiles.domain.model.queries;

import com.transport.app.platform.profiles.domain.model.valueobjects.EmailAddress;

public record GetProfileByEmailQuery(EmailAddress email) { }
