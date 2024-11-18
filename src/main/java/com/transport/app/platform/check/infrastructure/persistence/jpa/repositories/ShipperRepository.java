package com.transport.app.platform.check.infrastructure.persistence.jpa.repositories;

import com.transport.app.platform.check.domain.model.aggregates.Shipper;
import com.transport.app.platform.check.domain.model.valueobjects.ProfileId;
import com.transport.app.platform.check.domain.model.valueobjects.ShipperId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Long> {

    Optional<Shipper> findByShipperId(ShipperId shipperId);
    Optional<Shipper> findByProfileId(ProfileId profileId);

}
