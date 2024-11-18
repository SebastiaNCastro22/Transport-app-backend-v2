package com.transport.app.platform.iotProcess.infrastructure.persistence.jpa.repositories;

import com.transport.app.platform.iam.domain.model.aggregates.Client;
import com.transport.app.platform.iotProcess.domain.model.aggregates.IotProcess;
import com.transport.app.platform.iotProcess.domain.model.valueobjects.IotProcessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IotProcessRepository extends JpaRepository<IotProcess, Long> {
    Optional<Double> findTemperatureById(IotProcessId iotProcessId);
    Optional<Double> findWeightById(IotProcessId iotProcessId);
    Optional<IotProcess> findById(IotProcessId iotProcessId);
    Optional<IotProcess> findByMacAddress(String macAddress);
    Optional<IotProcess> findByNameIotDevice(String username);

}
