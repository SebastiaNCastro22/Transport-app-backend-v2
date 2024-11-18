package com.transport.app.platform.check.interfaces.rest.resources;

import com.transport.app.platform.check.domain.model.valueobjects.RequestStatus;

public record CreateRequestResource(String startLocation, String arrivalPlace, String descriptionRequest, Double idealTemperature, Double idealWeight, Double offeredPrice) {
}
