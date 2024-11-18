package com.transport.app.platform.check.domain.model.aggregates;

import com.transport.app.platform.check.domain.model.valueobjects.ShipmentProgress;
import com.transport.app.platform.check.domain.model.valueobjects.ShipmentStatus;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import com.transport.app.platform.check.infrastructure.persistence.jpa.repositories.TransporterRepository;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import io.jsonwebtoken.lang.Strings;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Shipment extends AuditableAbstractAggregateRoot<Shipment> {


    @Enumerated(EnumType.STRING)
    private ShipmentStatus status;

    @OneToOne
    @JoinColumn(name = "request_id", nullable = false)
    private Request request;

    @Getter
    @Embedded
    private TransporterId transporterId;


    @Embedded
    private ShipmentProgress shipmentProgress;

    public Shipment() {

    }

    public Shipment(TransporterId transporterId, Request request) {
        this();
        this.request = request;
        this.transporterId = transporterId;
        this.status = ShipmentStatus.REQUESTED;
        this.shipmentProgress = new ShipmentProgress();
    }

    public void confirm() {
        this.status = ShipmentStatus.CONFIRMED;
        //this.shipmentProgress.initializeProgressRecord(this, course.getLearningPath());
        // this.registerEvent(new EnrollmentConfirmedEvent(this));
    }

    public String getStatus() {
        return this.status.name().toLowerCase();
    }

    public void updateStatus(ShipmentStatus newStatus) {
        this.status = newStatus;
    }

    private String generateShipmentId() {
        return "SH-" + System.currentTimeMillis();
    }
}
