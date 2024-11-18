package com.transport.app.platform.check.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record RequestId(Long requestId) {
    public RequestId {
        if (requestId < 0) {
            throw new IllegalArgumentException("Request requestId cannot be negative");
        }
    }
    public RequestId() {
        this(0L);
    }
}