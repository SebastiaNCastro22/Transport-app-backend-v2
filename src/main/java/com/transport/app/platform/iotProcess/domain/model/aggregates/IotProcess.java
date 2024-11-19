package com.transport.app.platform.iotProcess.domain.model.aggregates;

import com.transport.app.platform.iotProcess.domain.model.commands.CreateIotProcessCommand;
import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class IotProcess extends AuditableAbstractAggregateRoot<IotProcess> {

    @Embedded
    @Column(name = "iot_id")
    private final IotProcessId iotProcessId ;

    private double temperature;
    private double weight;

    public IotProcess() {
        this.iotProcessId = new IotProcessId();
    }

    public IotProcess(Double temperature, Double weight) {
        this();
        this.temperature = temperature;
        this.weight = weight;
    }
    public IotProcess(CreateIotProcessCommand command) {
        this.iotProcessId = new IotProcessId();
        this.temperature = command.temperature();
        this.weight = command.weight();
    }

    public IotProcessId getIotDeviceId() {
        return iotProcessId;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getWeight() {
        return weight;
    }

    public void updateTemperature(double newTemperature) {
        this.temperature = newTemperature;
    }

    public void updateWeight(double newWeight) {
        this.weight = newWeight;
    }

}
