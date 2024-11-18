package com.transport.app.platform.iotProcess.domain.services;

import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.queries.GetAllIotProcessQuery;
import com.transport.app.platform.iotProcess.domain.model.queries.GetIotProcessByIdQuery;
import com.transport.app.platform.iotProcess.domain.model.queries.GetTemperatureQuery;
import com.transport.app.platform.iotProcess.domain.model.queries.GetWeightQuery;

import java.util.List;
import java.util.Optional;

public interface IotProcessQueryService {
    List<IotProcess> handle(GetAllIotProcessQuery query);
    Optional<IotProcess> handle(GetIotProcessByIdQuery query);
    Optional<Double> handle(GetTemperatureQuery query);
    Optional<Double> handle(GetWeightQuery query);
}
