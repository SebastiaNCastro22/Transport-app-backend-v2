package com.transport.app.platform.check.interfaces.rest.transform;

import com.transport.app.platform.check.domain.model.aggregates.Request;
import com.transport.app.platform.check.interfaces.rest.resources.RequestResource;


public class RequestResourceFromEntityAssembler {
    public static RequestResource toResourceFromEntity(Request entity) {
        return new RequestResource(entity.getId(), entity.getStartLocation(), entity.getArrivalPlace(), entity.getDescriptionRequest(), entity.getIdealTemperature(), entity.getIdealWeight(), entity.getOfferedPrice());
    }
}