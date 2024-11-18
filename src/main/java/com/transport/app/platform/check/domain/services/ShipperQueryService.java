package com.transport.app.platform.check.domain.services;


import com.transport.app.platform.check.domain.model.aggregates.Shipper;
import com.transport.app.platform.check.domain.model.queries.GetShipperByProfileIdQuery;
import com.transport.app.platform.check.domain.model.queries.GetShipperByShipperIdQuery;


import java.util.Optional;

public interface ShipperQueryService {
    Optional<Shipper> handle(GetShipperByProfileIdQuery query);
    Optional<Shipper> handle(GetShipperByShipperIdQuery query);

}
