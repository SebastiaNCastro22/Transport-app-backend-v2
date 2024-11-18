package com.transport.app.platform.iam.domain.services;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iam.domain.model.commands.SignInCommand;
import com.transport.app.platform.iam.domain.model.commands.SignUpCommand;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.util.Optional;

public interface ClientCommandService {
    Optional<Client> handle(SignUpCommand command);
    Optional<ImmutablePair<Client, String>> handle(SignInCommand command);
}
