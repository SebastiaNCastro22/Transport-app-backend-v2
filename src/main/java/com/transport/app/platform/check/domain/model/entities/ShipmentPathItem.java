/*package com.acme.center.platform.check.domain.model.entities;

import com.acme.center.platform.check.domain.model.aggregates.Request;
import com.acme.center.platform.check.domain.model.valueobjects.ShipmentPath;
import com.acme.center.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
/*
@Getter
@Entity
public class ShipmentPathItem extends AuditableModel {

   /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "request_id")
    @NotNull
    private Request request;

    @NotNull
    private Long routeId;

    @OneToOne
    @JoinColumn(name = "shipment_path_id", nullable = false)
    private ShipmentPathItem shipmentPathItem;

    public ShipmentPathItem(Request request, Long routeId, ShipmentPathItem shipmentPathItem) {
        this.request = request;
        this.routeId = routeId;
        this.shipmentPathItem = shipmentPathItem;
    }

    public ShipmentPathItem() {
        this.routeId = 0L;
        this.shipmentPathItem = null;
    }

    public void updateStatus(ShipmentPathItem shipmentPathItem) {
        this.shipmentPathItem = shipmentPathItem;
    }*/
//}