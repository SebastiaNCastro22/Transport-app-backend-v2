package com.transport.app.platform.iam.infrastructure.authorization.sfs.services;

import com.transport.app.platform.iam.infrastructure.authorization.sfs.model.ClientDetailsImpl;
import com.transport.app.platform.iam.infrastructure.persistence.jpa.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service(value = "defaultUserDetailsService")
public class ClientDetailsServiceImpl implements UserDetailsService {
    private final ClientRepository clientRepository;

    public ClientDetailsServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = clientRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return ClientDetailsImpl.build(user);
    }
}
