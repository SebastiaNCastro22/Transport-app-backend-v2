package com.transport.app.platform.iotProcess.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record IotProcessId(Long iotProcessId) {
    public IotProcessId {
        if (iotProcessId < 0) {
            throw new IllegalArgumentException("IotProcess iotProcessId cannot be negative");
        }
    }

    public IotProcessId() {
        this(0L);
    }
}
