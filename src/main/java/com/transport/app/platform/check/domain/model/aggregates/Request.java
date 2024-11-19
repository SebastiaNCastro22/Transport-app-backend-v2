package com.transport.app.platform.check.domain.model.aggregates;

import com.transport.app.platform.check.domain.model.commands.CreateRequestCommand;
import com.transport.app.platform.check.domain.model.valueobjects.RequestStatus;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import io.jsonwebtoken.lang.Strings;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Request extends AuditableAbstractAggregateRoot<Request> {


    @Getter
    @Embedded

    private String startLocation;
    private String arrivalPlace;
    private Double offeredPrice;
    private Double idealTemperature;
    private Double idealWeight;
    private String descriptionRequest;
    @Enumerated(EnumType.STRING)
    private RequestStatus status;


    public Request() {
        this.startLocation = Strings.EMPTY;
        this.arrivalPlace = Strings.EMPTY;
        this.offeredPrice = null;
        this.idealTemperature = null;
        this.idealWeight = null;
        this.descriptionRequest = Strings.EMPTY;

    }

    public Request(String startLocation, String arrivalPlace, String descriptionRequest, Double idealTemperature, Double idealWeight, Double offeredPrice, RequestStatus status) {
        this();
        this.startLocation = startLocation;
        this.arrivalPlace = arrivalPlace;
        this.offeredPrice = offeredPrice;
        this.idealTemperature = idealTemperature;
        this.idealWeight = idealWeight;
       // this.status = status;
        this.descriptionRequest = descriptionRequest;
    }

    public Request(CreateRequestCommand command) {
        this();
        this.startLocation = command.startLocation();
        this.arrivalPlace = command.arrivalPlace();
        this.offeredPrice = command.offeredPrice();
        this.idealTemperature = command.idealTemperature();
        this.idealWeight = command.idealWeight();
        //this.status = command.status();
        this.descriptionRequest = command.descriptionRequest();
    }

    public Request updateInformation(String startLocation, String arrivalPlace) {
        this.startLocation = startLocation;
        this.arrivalPlace = arrivalPlace;
        return this;
    }

    public void updateTemperature(Double idealTemperature) {
        this.idealTemperature = idealTemperature;
    }

    public void updateWeight(Double idealWeight) {
        this.idealWeight = idealWeight;
    }
}
