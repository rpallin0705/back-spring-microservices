package com.microservice.auth.application.service.impl;

import com.microservice.auth.application.service.DeviceService;
import com.microservice.auth.domain.model.Device;
import com.microservice.auth.domain.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository repository;

    public DeviceServiceImpl(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Device> findByDeviceId(String deviceId) {
        return repository.findByDeviceId(deviceId);
    }
}