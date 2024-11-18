package com.transport.app.platform.check.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

import java.util.UUID;

@Embeddable
public record TransporterId(String trasnporterId) {
    public TransporterId() {
        this(UUID.randomUUID().toString());
    }
    public TransporterId {
        if (trasnporterId == null || trasnporterId.isBlank()) {
            throw new IllegalArgumentException("Transporter profileId cannot be null or blank");

        }

    }
}