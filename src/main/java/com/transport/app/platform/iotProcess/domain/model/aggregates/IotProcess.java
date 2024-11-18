package com.transport.app.platform.iotProcess.domain.model.aggregates;

import com.transport.app.platform.iotProcess.domain.model.commands.CreateIotProcessCommand;
import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import com.transport.app.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class IotProcess extends AuditableAbstractAggregateRoot<IotProcess> {

    @Embedded
    @Column(name = "iot_id")
    private final IotProcessId iotProcessId ;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String nameIotDevice;

    @NotBlank
    @Size(max = 120)
    private String macAddress;

    private double temperature;
    private double weight;

    public IotProcess() {
        this.iotProcessId = new IotProcessId();
    }

    public IotProcess(String nameIotDevice, String macAddress, Double temperature, Double weight) {
        this();
        this.nameIotDevice = nameIotDevice;
        this.macAddress = macAddress;
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

    public String getMacAddress() {
        return macAddress;
    }

    public String getNameIotDevice() {
        return nameIotDevice;
    }
}
