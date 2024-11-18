package com.transport.app.platform.check.interfaces.rest.resources;

public record RequestResource(long id, String startLocation, String arrivalPlace, String descriptionRequest, Double idealTemperature, Double idealWeight, Double offeredPrice) {
}
