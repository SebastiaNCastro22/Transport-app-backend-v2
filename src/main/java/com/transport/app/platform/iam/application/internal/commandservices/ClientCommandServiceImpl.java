package com.transport.app.platform.iam.application.internal.commandservices;

import com.transport.app.platform.iam.application.internal.outboundservices.hashing.HashingService;
import com.transport.app.platform.iam.application.internal.outboundservices.tokens.TokenService;
import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iam.domain.model.commands.SignInCommand;
import com.transport.app.platform.iam.domain.model.commands.SignUpCommand;
import com.transport.app.platform.iam.domain.model.valueobjects.Roles;
import com.transport.app.platform.iam.domain.services.ClientCommandService;
import com.transport.app.platform.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.transport.app.platform.iam.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientCommandServiceImpl implements ClientCommandService {
    private final ClientRepository clientRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public ClientCommandServiceImpl(ClientRepository clientRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.clientRepository = clientRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<Client> handle(SignUpCommand command) {
        if (clientRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles();
        if (roles.isEmpty()) {
            var role = roleRepository.findByName(Roles.ROLE_CLIENT);
            roles.add(role.get());
        }
        roles = command.roles().stream()
                .map(role -> roleRepository.findByName(role.getName())
                        .orElseThrow(() -> new RuntimeException("Role not found"))).toList();
        var user = new Client(command.username(), hashingService.encode(command.password()), roles);
        clientRepository.save(user);
        return clientRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<Client, String>> handle(SignInCommand command) {
        var user = clientRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var currentUser = user.get();
        var token = tokenService.generateToken(currentUser.getUsername());
        return Optional.of(ImmutablePair.of(currentUser, token));
    }
}
