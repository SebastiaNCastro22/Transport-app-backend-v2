package com.transport.app.platform.check.domain.model.valueobjects;

import com.transport.app.platform.check.domain.model.aggregates.Shipment;
import com.transport.app.platform.check.domain.model.entities.ShipmentProgressItem;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ShipmentProgress  {

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private List<ShipmentProgressItem> shipmentProgressItems;

    public ShipmentProgress() {
        shipmentProgressItems = new ArrayList<>();
    }

    public long calculateDaysElapsedForEnrollment(Shipment shipment) {
        return shipmentProgressItems.stream().filter(progressRecordItem -> progressRecordItem.getShipment().equals(shipment))
                .mapToLong(ShipmentProgressItem::calculateDaysElapsed).sum();
    }

}
