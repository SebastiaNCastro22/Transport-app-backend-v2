package com.transport.app.platform.check.infrastructure.persistence.jpa.repositories;

import com.transport.app.platform.check.domain.model.aggregates.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RequestRepository extends JpaRepository<Request, Long>  {

    Optional<Request> findById(Long requestId);
    Optional<Request> findByArrivalPlace(String arrivalPlace);
    Optional<Request> findByStartLocation(String startLocation);

}
