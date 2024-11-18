package com.transport.app.platform.iam.infrastructure.authorization.sfs.model;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@EqualsAndHashCode
public class ClientDetailsImpl implements UserDetails {

    private final String username;
    @JsonIgnore
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private final Collection<? extends GrantedAuthority> authorities;

    public ClientDetailsImpl(String username, String password,
                           Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        this.authorities = authorities;
    }

    public static ClientDetailsImpl build(Client user) {
        var authorities = user.getRoles().stream()
                .map(role -> role.getName().name())
                .map(SimpleGrantedAuthority::new)
                .toList();
        return new ClientDetailsImpl(user.getUsername(), user.getPassword(), authorities);
    }

}
