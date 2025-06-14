package com.microservice.auth.application.service;

import com.microservice.auth.domain.model.Device;

import java.util.Optional;

public interface DeviceService {
    Optional<Device> findByDeviceId(String deviceId);
}