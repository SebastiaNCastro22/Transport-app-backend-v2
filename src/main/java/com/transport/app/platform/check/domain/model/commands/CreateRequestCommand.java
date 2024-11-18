package com.transport.app.platform.check.domain.model.commands;

import com.transport.app.platform.check.domain.model.valueobjects.RequestStatus;

public record CreateRequestCommand(String startLocation, String arrivalPlace, String descriptionRequest, Double idealTemperature, Double idealWeight, Double offeredPrice) {
}
