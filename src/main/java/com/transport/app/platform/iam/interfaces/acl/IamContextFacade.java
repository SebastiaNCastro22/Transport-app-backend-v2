package com.transport.app.platform.iam.interfaces.acl;

import com.transport.app.platform.iam.domain.model.commands.SignUpCommand;
import com.transport.app.platform.iam.domain.model.entities.Role;
import com.transport.app.platform.iam.domain.model.queries.GetClientByIdQuery;
import com.transport.app.platform.iam.domain.model.queries.GetClientByUsernameQuery;
import com.transport.app.platform.iam.domain.services.ClientCommandService;
import com.transport.app.platform.iam.domain.services.ClientQueryService;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * IamContextFacade
 * <p>
 *     This class is a facade for the IAM context. It provides a simple interface for other bounded contexts to interact with the
 *     IAM context.
 *     This class is a part of the ACL layer.
 * </p>
 *
 */
public class IamContextFacade {
    private final ClientCommandService clientCommandService;
    private final ClientQueryService clientQueryService;

    public IamContextFacade(ClientCommandService clientCommandService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.clientQueryService = clientQueryService;
    }

    /**
     * Creates a user with the given username and password.
     * @param username The username of the user.
     * @param password The password of the user.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password) {
        var signUpCommand = new SignUpCommand(username, password, List.of(Role.getDefaultRole()));
        var result = clientCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Creates a user with the given username, password and roles.
     * @param username The username of the user.
     * @param password The password of the user.
     * @param roleNames The names of the roles of the user. When a role does not exist, it is ignored.
     * @return The id of the created user.
     */
    public Long createUser(String username, String password, List<String> roleNames) {
        var roles = roleNames != null ? roleNames.stream().map(Role::toRoleFromName).toList() : new ArrayList<Role>();
        var signUpCommand = new SignUpCommand(username, password, roles);
        var result = clientCommandService.handle(signUpCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the id of the user with the given username.
     * @param username The username of the user.
     * @return The id of the user.
     */
    public Long fetchUserIdByUsername(String username) {
        var getUserByUsernameQuery = new GetClientByUsernameQuery(username);
        var result = clientQueryService.handle(getUserByUsernameQuery);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }

    /**
     * Fetches the username of the user with the given id.
     * @param userId The id of the user.
     * @return The username of the user.
     */
    public String fetchUsernameByUserId(Long userId) {
        var getUserByIdQuery = new GetClientByIdQuery(userId);
        var result = clientQueryService.handle(getUserByIdQuery);
        if (result.isEmpty()) return Strings.EMPTY;
        return result.get().getUsername();
    }

}