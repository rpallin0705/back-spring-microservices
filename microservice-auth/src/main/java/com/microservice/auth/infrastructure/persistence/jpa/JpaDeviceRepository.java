package com.microservice.auth.infrastructure.persistence.jpa;

import com.microservice.auth.domain.model.Device;
import com.microservice.auth.domain.repository.DeviceRepository;
import com.microservice.auth.infrastructure.persistence.entity.DeviceEntity;
import com.microservice.auth.infrastructure.persistence.springdata.SpringDataDeviceRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaDeviceRepository implements DeviceRepository {

    private final SpringDataDeviceRepository springRepo;

    public JpaDeviceRepository(SpringDataDeviceRepository springRepo) {
        this.springRepo = springRepo;
    }

    @Override
    public Optional<Device> findByDeviceId(String deviceId) {
        return springRepo.findByDeviceId(deviceId)
                .map(e -> new Device(e.getId(), e.getDeviceId(), e.getSecret()));
    }
}