package com.kdu.smarthome.repository;

import com.kdu.smarthome.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HouseRepository extends JpaRepository<House,Long> {
    Optional<House> findByHouseId(long houseId);
}
