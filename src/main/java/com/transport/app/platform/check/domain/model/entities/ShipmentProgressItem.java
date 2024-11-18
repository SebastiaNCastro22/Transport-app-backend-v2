package com.transport.app.platform.check.domain.model.entities;

import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.domain.model.valueobjects.ProgressStatus;
import com.transport.app.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Entity
public class ShipmentProgressItem extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "shipment_id")
    private Shipment shipment;

    @Getter
    private Long routeId;
    private ProgressStatus status;
    private Date startedAt;
    private Date completedAt;

    public ShipmentProgressItem(Shipment shipment, Long routeId) {
        this.shipment = shipment;
        this.routeId = routeId;
        this.status = ProgressStatus.NOT_STARTED;
    }

    public ShipmentProgressItem() {

    }

    public void start() {
        this.status = ProgressStatus.STARTED;
        this.startedAt = new Date();
    }
    public void complete() {
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = new Date();
    }
    public boolean isCompleted() {
        return this.status == ProgressStatus.COMPLETED;
    }
    public boolean isInProgress() {
        return this.status == ProgressStatus.STARTED;
    }
    public boolean isNotStarted() {
        return this.status == ProgressStatus.NOT_STARTED;
    }
    public long calculateDaysElapsed() {

        if (this.status == ProgressStatus.NOT_STARTED) return 0;
        var defaultTimeZone = java.time.ZoneId.systemDefault();
        var fromDate = this.startedAt.toInstant();
        var toDate = this.completedAt == null ? LocalDate.now().atStartOfDay(defaultTimeZone).toInstant() : this.completedAt.toInstant();
        return java.time.Duration.between(fromDate, toDate).toDays();
    }
}
