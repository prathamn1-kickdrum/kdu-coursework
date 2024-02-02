package com.example.spring.handson.repository;

import com.example.spring.handson.entity.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShiftTypeRepository extends JpaRepository<ShiftType, UUID> {
    List<ShiftType> findByTenantId(UUID tenantId);
}
