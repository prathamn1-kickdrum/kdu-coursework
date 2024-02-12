package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, String> {
    Optional<Device> findByKickstonId(String kickstonId);
}
