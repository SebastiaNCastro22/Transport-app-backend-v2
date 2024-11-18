package com.transport.app.platform.check.domain.model.valueobjects;

import java.util.UUID;

public record ShipperId(String shipperId){

    public ShipperId() {
        this(UUID.randomUUID().toString());
    }
    public ShipperId {
        if (shipperId == null || shipperId.isBlank()) {
            throw new IllegalArgumentException("User profileId cannot be null or blank");

        }
    }

}
