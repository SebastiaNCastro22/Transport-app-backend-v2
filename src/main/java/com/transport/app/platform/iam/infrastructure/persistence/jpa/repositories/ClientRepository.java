package com.transport.app.platform.iam.infrastructure.persistence.jpa.repositories;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByUsername(String username);
    boolean existsByUsername(String username);
}
