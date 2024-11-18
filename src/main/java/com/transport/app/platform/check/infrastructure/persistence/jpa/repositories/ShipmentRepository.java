package com.transport.app.platform.check.infrastructure.persistence.jpa.repositories;

import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {

    List<Shipment> findAllByTransporterId(TransporterId transporterId);
    List<Shipment> findAllByRequestId(Long requestId);
    Optional<Shipment> findByTransporterIdAndRequestId(TransporterId transporterId, Long requestId);
    Optional<Shipment> findById(Long shipmentId);
    //List<Shipment> findAllByClientId(Long clientId);

}
