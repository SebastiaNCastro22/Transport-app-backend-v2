package com.transport.app.platform.check.application.internal.queryservices;



import com.transport.app.platform.check.domain.model.aggregates.Shipper;
import com.transport.app.platform.check.domain.model.queries.GetShipperByProfileIdQuery;
import com.transport.app.platform.check.domain.model.queries.GetShipperByShipperIdQuery;
import com.transport.app.platform.check.domain.services.ShipperQueryService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.ShipperRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShipperQueryServiceImpl implements ShipperQueryService {

    private final ShipperRepository shipperRepository;

    public ShipperQueryServiceImpl(ShipperRepository shipperRepository) {
        this.shipperRepository = shipperRepository;
    }

    @Override
    public Optional<Shipper> handle(GetShipperByProfileIdQuery query) {
        return shipperRepository.findByProfileId(query.profileId());
    }
    @Override
    public Optional<Shipper> handle(GetShipperByShipperIdQuery query) {
        return shipperRepository.findByShipperId(query.shipperId());
    }
}
