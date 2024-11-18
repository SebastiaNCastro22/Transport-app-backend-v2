/*package com.acme.center.platform.check.domain.model.valueobjects;

import com.acme.center.platform.check.domain.model.aggregates.Request;
import com.acme.center.platform.check.domain.model.aggregates.Shipment;
import com.acme.center.platform.check.domain.model.entities.ShipmentPathItem;
import com.acme.center.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ShipmentPath {

    @OneToOne(mappedBy = "request", cascade = CascadeType.ALL)
    private ShipmentPathItem shipmentPathItems;

    public ShipmentPath() {
        this.shipmentPathItems = new ShipmentPathItem();
    }


}*/
