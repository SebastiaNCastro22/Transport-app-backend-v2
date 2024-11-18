package com.transport.app.platform.iotProcess.application.internal.queryservices;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.queries.GetAllIotProcessQuery;
import com.transport.app.platform.iotProcess.domain.model.queries.GetIotProcessByIdQuery;
import com.transport.app.platform.iotProcess.domain.model.queries.GetTemperatureQuery;
import com.transport.app.platform.iotProcess.domain.model.queries.GetWeightQuery;
import com.transport.app.platform.iotProcess.domain.services.IotProcessQueryService;
import com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories.IotProcessRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IotProcessQueryServiceImpl implements IotProcessQueryService {

    private final IotProcessRepository iotProcessRepository;

    public IotProcessQueryServiceImpl(IotProcessRepository iotProcessRepository) {
        this.iotProcessRepository = iotProcessRepository;
    }

    @Override
    public List<IotProcess> handle(GetAllIotProcessQuery query) {
        return iotProcessRepository.findAll();
    }

    @Override
    public Optional<IotProcess> handle(GetIotProcessByIdQuery query) {
        return iotProcessRepository.findById(query.iotProcessId());
    }

    @Override
    public Optional<Double> handle(GetTemperatureQuery query) {
        return iotProcessRepository.findTemperatureById(query.iotProcessId());
    }

    @Override
    public Optional<Double> handle(GetWeightQuery query) {
        return iotProcessRepository.findWeightById(query.iotProcessId());
    }

}
