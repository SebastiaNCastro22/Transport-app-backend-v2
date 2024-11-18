package com.transport.app.platform.check.domain.model.aggregates;

import com.transport.app.platform.check.domain.model.valueobjects.ProfileId;
import com.transport.app.platform.check.domain.model.valueobjects.ShipperId;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class Shipper extends AuditableAbstractAggregateRoot<Shipper> {

    @Embedded
    @Column(name = "shipper_id")
    private final ShipperId shipperId ;

    @Embedded
    private ProfileId profileId;

    public Shipper() {
        this.shipperId = new ShipperId();
    }

    public Shipper(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public Shipper(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }


    public Long getProfileId() {
        return this.profileId.profileId();
    }

    public ShipperId getShipperId() { return shipperId; }
}
