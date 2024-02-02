package com.example.spring.handson.service;

import com.example.spring.handson.dao.TenantDAO;
import com.example.spring.handson.dto.TenantDTO;
import com.example.spring.handson.model.Tenant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TenantService {

    private final TenantDAO tenantDAO;

public void addTenant(TenantDTO tenantDTO){
        Tenant tenant = mapTenantDTOToTenant(tenantDTO);
        tenantDAO.saveTenant(tenant);
    }

    public Tenant mapTenantDTOToTenant(TenantDTO tenantDTO) {
        Tenant tenant = new Tenant();
        tenant.setId(UUID.randomUUID());
        tenant.setName(tenantDTO.getName());
        tenant.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        tenant.setCreatedBy(tenantDTO.getCreatedBy());
        tenant.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        tenant.setUpdatedBy(tenantDTO.getUpdatedBy());
        return tenant;
    }


}
