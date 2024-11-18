package com.transport.app.platform.check.infrastructure.persistence.jpa.repositories;

import com.transport.app.platform.check.domain.model.aggregates.Transporter;
import com.transport.app.platform.check.domain.model.valueobjects.ProfileId;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransporterRepository extends JpaRepository<Transporter, Long> {

    Optional<Transporter> findByTransporterId(TransporterId transporterId);
    Optional<Transporter> findByProfileId(ProfileId profileId);


}
