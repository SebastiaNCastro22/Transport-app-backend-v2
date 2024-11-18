package com.transport.app.platform.check.domain.services;

import com.transport.app.platform.check.domain.model.aggregates.Transporter;
import com.transport.app.platform.check.domain.model.queries.GetTransporteByProfileIdQuery;
import com.transport.app.platform.check.domain.model.queries.GetTransporterByTransporterIdQuery;

import java.util.Optional;

public interface TransporterQueryService {
    Optional<Transporter> handle(GetTransporteByProfileIdQuery query);
    Optional<Transporter> handle(GetTransporterByTransporterIdQuery query);
}
