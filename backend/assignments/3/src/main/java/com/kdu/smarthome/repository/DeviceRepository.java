package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}
