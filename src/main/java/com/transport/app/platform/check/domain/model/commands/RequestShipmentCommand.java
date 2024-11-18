package com.transport.app.platform.check.domain.model.commands;

import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;

public record RequestShipmentCommand(TransporterId transporterId, long requestId){
}
