package com.microservice.auth.domain.repository;

import com.microservice.auth.domain.model.Device;

import java.util.Optional;

public interface DeviceRepository {
    Optional<Device> findByDeviceId(String deviceId);
}