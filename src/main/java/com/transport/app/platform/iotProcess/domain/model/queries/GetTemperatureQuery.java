package com.transport.app.platform.iotProcess.domain.model.queries;

import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;

public record GetTemperatureQuery(IotProcessId iotProcessId) {
}
