package com.transport.app.platform.check.domain.model.aggregates;
import com.transport.app.platform.check.domain.model.valueobjects.ProfileId;
import com.transport.app.platform.check.domain.model.valueobjects.TransporterId;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;


@Entity
public class Transporter extends AuditableAbstractAggregateRoot<Transporter> {


    @Embedded
    @Column(name = "trasnporter_id")
    private final TransporterId transporterId ;

    @Embedded
    private ProfileId profileId;

    public Transporter() {
        this.transporterId = new TransporterId();
    }

    public Transporter(Long profileId) {
        this();
        this.profileId = new ProfileId(profileId);
    }

    public Transporter(ProfileId profileId) {
        this();
        this.profileId = profileId;
    }

    public TransporterId getTransporterId() {
        return transporterId;
    }

    public Long getProfileId() {
        return this.profileId.profileId();
    }

}
