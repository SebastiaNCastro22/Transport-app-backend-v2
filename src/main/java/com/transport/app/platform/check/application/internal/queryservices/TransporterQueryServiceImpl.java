package com.transport.app.platform.check.application.internal.queryservices;

import com.transport.app.platform.check.domain.model.aggregates.Transporter;
import com.transport.app.platform.check.domain.model.queries.GetTransporteByProfileIdQuery;
import com.transport.app.platform.check.domain.model.queries.GetTransporterByTransporterIdQuery;
import com.transport.app.platform.check.domain.services.TransporterQueryService;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.TransporterRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransporterQueryServiceImpl implements TransporterQueryService {

    private final TransporterRepository transporterRepository;

    public TransporterQueryServiceImpl(TransporterRepository transporterRepository) {
        this.transporterRepository = transporterRepository;
    }

    @Override
    public Optional<Transporter> handle(GetTransporteByProfileIdQuery query) {
        return transporterRepository.findByProfileId(query.profileId());
    }
    @Override
    public Optional<Transporter> handle(GetTransporterByTransporterIdQuery query) {
        return transporterRepository.findByTransporterId(query.transporterId());
    }
}
