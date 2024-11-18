package com.transport.app.platform.check.domain.model.commands;

public record UpdateRequestCommand(
        Long iotProcessId, // Añadir IotProcessId
        Double updatedTemperature,
        Double updatedWeight
) {}